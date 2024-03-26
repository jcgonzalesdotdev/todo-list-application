
<script>
import TaskService from '../service/TaskService'
import TaskCreate from './modal/TaskCreate.vue'
import TaskUpdate from './modal/TaskUpdate.vue'
import TaskDelete from './modal/TaskDelete.vue'
import TaskView from './modal/TaskView.vue'
import { CONSTANTS } from '@/utils/constants'
import { SCREEN_MODIFIERS } from '@/utils/screenmodifiers'
import { FILTERS } from '@/utils/filters'
import { COMMON_UTILS } from '@/utils/common'
// import micSvg from '@/assets/images/circle-microphone.svg'

export default {
  name: 'App',
  components: {
    TaskCreate,
    TaskUpdate,
    TaskDelete,
    TaskView
    // micSvg,
  },
  data() {
    return {
      taskId: '',
      screenLabels: {
        taskId: '',
        taskTitle: '',
        taskDesciption: '',
        taskStart_date: '',
        taskEnd_date: '',
        taskStatus: ''
      },
      cbLanguage: false,
      tasks: [],
      searchInput: '',
      selectedTask: null, // To hold the task selected for viewing
      showViewModal: false,
      transcript: '',
      pageSize: 5, // Number of tasks per page
      currentPage: 1 // Current page
    }
  },
  computed: {
    filteredTasks() {
      this.currentPage = 1
      if (this.searchInput === '') {
        return this.tasks
      } else {
        return this.tasks.filter((task) => {
          // Ensure task is not null or undefined
          if (!task) return false

          // Convert all properties to strings and apply .toLowerCase()
          const title = task.title ? task.title.toString().toLowerCase() : ''
          const description = task.description ? task.description.toString().toLowerCase() : ''
          const start_date = task.start_date ? task.start_date.toString().toLowerCase() : ''
          const end_date = task.end_date ? task.end_date.toString().toLowerCase() : ''
          const status = task.status ? task.status.toString().toLowerCase() : ''

          // Check if any of the properties contain the search input
          FILTERS.startsXdaysFromNow(this.searchInput, this.cbLanguage)
          //Custom filters
          //#1 WHEN START DATE IS TOMORROW
          if (FILTERS.startsTomorrow(this.searchInput, this.cbLanguage)) {
            return start_date.includes(FILTERS.daysFilter(this.cbLanguage, 1))

            //#2 WHEN START DATE WAS YESTERDAY
          } else if (FILTERS.startedYesterday(this.searchInput, this.cbLanguage)) {
            return start_date.includes(FILTERS.daysFilter(this.cbLanguage, -1))

            //#3 WHEN START DATE STARTS TODAY
          } else if (FILTERS.startsToday(this.searchInput, this.cbLanguage)) {
            return start_date.includes(FILTERS.daysFilter(this.cbLanguage, 0))

            //#4 WHEN START DATE STARTS X DAYS FROM NOW,THIS FILTER IS FOR EN ONLY
          } else if (FILTERS.startsXdaysFromNow(this.searchInput)) {
            let wordsArr = this.searchInput.toLowerCase().split(CONSTANTS.WHITE_SPACE)
            let convertedNumber = COMMON_UTILS.getConvertedNumber(CONSTANTS.numberMap, wordsArr)
            return start_date.includes(FILTERS.daysFilter(this.cbLanguage, convertedNumber))

            //#5 WHEN END DATE ENDS X DAYS FROM NOW,THIS FILTER IS FOR EN ONLY
          } else if (FILTERS.endsXdaysFromNow(this.searchInput)) {
            let wordsArr = this.searchInput.toLowerCase().split(CONSTANTS.WHITE_SPACE)
            let convertedNumber = COMMON_UTILS.getConvertedNumber(CONSTANTS.numberMap, wordsArr)

            return end_date.includes(FILTERS.daysFilter(this.cbLanguage, convertedNumber))
          } else {
            //search input conditions
            return (
              title.includes(this.searchInput.toLowerCase()) ||
              description.includes(this.searchInput.toLowerCase()) ||
              start_date.includes(this.searchInput.toLowerCase()) ||
              end_date.includes(this.searchInput.toLowerCase()) ||
              status.includes(this.searchInput.toLowerCase())
            )
          }
        })
      }
    },
    viewModalCheck() {
      return this.showViewModal
    },
    totalPages() {
      return Math.ceil(this.filteredTasks.length / this.pageSize)
    },
    paginatedTasks() {
      const startIndex = (this.currentPage - 1) * this.pageSize
      const endIndex = startIndex + this.pageSize
      return this.filteredTasks.slice(startIndex, endIndex)
    }
  },
  methods: {
    init() {
      TaskService.init().then((response) => {
        //Set response data to tasks[] data
        this.tasks = response.data
        //Set screen labels, default language is EN
        const searchInput = document.getElementById('searchInput')
        //If true, set screen labels to JP
        if (this.cbLanguage === true) {
          this.screenLabels = SCREEN_MODIFIERS.setScreenLabelsJp(this.screenLabels, CONSTANTS)
          searchInput.placeholder = CONSTANTS.JP.lbl_search_jp

          this.tasks.forEach((task) => {
            task.start_date = COMMON_UTILS.convertToJapaneseDate(task.start_date)
            task.end_date = COMMON_UTILS.convertToJapaneseDate(task.end_date)
          })
        } else {
          //Set screen labels to EN
          this.screenLabels = SCREEN_MODIFIERS.setScreenLabelsEn(this.screenLabels, CONSTANTS)
          searchInput.placeholder = CONSTANTS.EN.lbl_search_en
        }
      })
    },
    handleLanguageChange() {
      const searchInput = document.getElementById('searchInput')

      //If true, set screen labels to JP
      if (this.cbLanguage === true) {
        this.screenLabels = SCREEN_MODIFIERS.setScreenLabelsJp(this.screenLabels, CONSTANTS)
        searchInput.placeholder = CONSTANTS.JP.lbl_search_jp

        this.filteredTasks.forEach((task) => {
          task.start_date = COMMON_UTILS.convertToJapaneseDate(task.start_date)
          task.end_date = COMMON_UTILS.convertToJapaneseDate(task.end_date)
        })
      } else {
        //Set screen labels to EN
        this.screenLabels = SCREEN_MODIFIERS.setScreenLabelsEn(this.screenLabels, CONSTANTS)
        searchInput.placeholder = CONSTANTS.EN.lbl_search_en
        this.filteredTasks.forEach((task) => {
          task.start_date = COMMON_UTILS.convertToEnglishDate(task.start_date)
          task.end_date = COMMON_UTILS.convertToEnglishDate(task.end_date)
        })
      }
    },
    handleTaskUpdated(updatedTask) {
      this.init()
    },
    handleTaskCreated(createdTask) {
      this.init()
    },
    handleTaskDeleted(response) {
      this.init()
    },
    handleTaskViewed(task) {
      // Set selectedTask when a row is clicked
      this.selectedTask = task
      this.showViewModal = !this.showViewModal
    },
    closeModal() {
      this.showViewModal = false
    },
    startListening() {
      // Create a new speech recognition instance
      const recognition = new webkitSpeechRecognition() || new SpeechRecognition()

      // Set the language
      // if checkbox is not selected(false), set language to EN.
      if (this.cbLanguage === false) {
        recognition.lang = 'en-US'
      } else {
        recognition.lang = 'ja-JP'
      }
      // Start listening
      recognition.start()

      recognition.onresult = (event) => {
        let finalTranscript = ''
        for (let i = event.resultIndex; i < event.results.length; i++) {
          if (event.results[i].isFinal) {
            // Append the recognized word to the final transcript
            finalTranscript += event.results[i][0].transcript
          }
        }
        // Update the transcript with the final transcript (remove leading/trailing spaces)
        this.transcript = finalTranscript.trim()
        this.searchInput = this.transcript
        this.searchInput = this.searchInput.replace('.', '')
        this.searchInput = this.searchInput.replace('。', '')
      }

      recognition.onerror = (event) => {
        recognition.stop()
        const audio = new Audio('src/assets/audio/speech-error.mp3');
        audio.play()
      }
    },
    nextPage() {
      if (this.currentPage < this.totalPages) {
        this.currentPage++
      }
    },
    prevPage() {
      if (this.currentPage > 1) {
        this.currentPage--
      }
    }
  },
  created() {
    this.init()
  }
}
</script>
<template>
  <nav class="navbar">
    <div class="container mx-6">
      <input
        id="searchInput"
        class="form-control mr-sm-2 search-input"
        type="text"
        aria-label="Search"
        style="width: 70% !important"
        v-model="searchInput"
      />
      <button class="general-button d-flex align-items-center" @click="startListening">
        <!-- <i class="fi fi-rs-circle-microphone"></i> -->
        <i class="bx bx-microphone size-icon"></i>
      </button>
      <TaskCreate @task-created="handleTaskCreated" />
      <div class="button r m-0" id="button-3">
        <input
          type="checkbox"
          class="checkbox"
          v-model="cbLanguage"
          @change="handleLanguageChange"
        />
        <div class="knobs"></div>
        <div class="layer"></div>
      </div>
    </div>
  </nav>
  <div class="container">
    <div class="tbl-container table-responsive bdr">
      <table class="table task-table table-hover">
        <thead class="thead-dark">
          <tr class="table-primary">
            <th scope="col">{{ screenLabels.taskId }}</th>
            <th scope="col">{{ screenLabels.taskTitle }}</th>
            <th scope="col">{{ screenLabels.taskDesciption }}</th>
            <th scope="col">{{ screenLabels.taskStart_date }}</th>
            <th scope="col">{{ screenLabels.taskEnd_date }}</th>
            <th scope="col">{{ screenLabels.taskStatus }}</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in paginatedTasks" :key="task.id">
            <td @click="handleTaskViewed(task)">{{ task.id }}</td>
            <td class="truncate" @click="handleTaskViewed(task)" v-tooltip="task.title">
              {{ task.title }}
            </td>
            <td class="truncate" @click="handleTaskViewed(task)" v-tooltip="task.description">
              {{ task.description }}
            </td>
            <td @click="handleTaskViewed(task)">{{ task.start_date }}</td>
            <td @click="handleTaskViewed(task)">{{ task.end_date }}</td>
            <td @click="handleTaskViewed(task)">{{ task.status }}</td>
            <td>
              <TaskUpdate
                :selectedTask="task"
                :dateFormatCheck="cbLanguage"
                @task-updated="handleTaskUpdated"
              />
            </td>
            <td>
              <TaskDelete :taskId="task.id" @task-deleted="handleTaskDeleted" />
            </td>
          </tr>
        </tbody>
      </table>
      <!-- Pagination -->
      <div class="pagination">
        <!-- Previous page button -->
        <button class="general-button" @click="prevPage" :disabled="currentPage === 1">
          <i class="bx bx-chevron-left"></i>
        </button>
        <!-- Display current page and total pages -->
        <span>&nbsp;{{ currentPage }} / {{ totalPages }}&nbsp;</span>
        <!-- Next page button -->
        <button class="general-button" @click="nextPage" :disabled="currentPage === totalPages">
          <i class="bx bx-chevron-right"></i>
        </button>
      </div>
      <TaskView
        :viewModalCheck="viewModalCheck"
        :selectedTask="selectedTask"
        @close-modal="closeModal"
      />
    </div>
  </div>
</template>

<style>
.container {
  position: relative;
  z-index: 2;
}

.general-button {
  appearance: none;
  background-color: transparent;
  border: 0.125em solid #1a1a1a;
  border-radius: 0.9375em !important;
  box-sizing: border-box;
  color: #3b3b3b;
  cursor: pointer;
  display: inline-block;
  font-family: Roobert, -apple-system, BlinkMacSystemFont, 'Segoe UI', Helvetica, Arial, sans-serif,
    'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol';
  font-size: 12px !important;
  font-weight: 600;
  line-height: normal;
  margin: 0;
  min-height: 3.75em;
  min-width: 0;
  outline: none;
  padding: 1em 1.5em;
  text-align: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;
}

.general-button:disabled {
  pointer-events: none;
}

.general-button:hover {
  color: #fff;
  background-color: #1a1a1a;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

.general-button:active {
  box-shadow: none;
  transform: translateY(0);
}

.task-table {
  margin-top: 2em;
  font-family: monospace;
  box-shadow: 30px 30px 15px -6px rgba(0, 0, 0, 0.1), 0px 10px 15px -3px rgba(0, 0, 0, 0.1);
}

.bdr {
  border-radius: 6px;
  overflow: hidden;
}

/* san gamit si close? */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
/* san gamit si close? */

.truncate {
  white-space: nowrap;
  overflow: hidden;
  text-overflow: ellipsis;
  max-width: 150px; /* Adjust max width of td title and description */
}
.toggle-button-cover {
  display: table-cell;
  position: relative;
  width: 200px;
  height: 140px;
  box-sizing: border-box;
}

.button-cover {
  height: 100px;
  margin: 20px;
  background-color: #fff;
  box-shadow: 0 10px 20px -8px #c5d6d6;
  border-radius: 4px;
}

.button-cover:before {
  counter-increment: button-counter;
  content: counter(button-counter);
  position: absolute;
  right: 0;
  bottom: 0;
  color: #d7e3e3;
  font-size: 12px;
  line-height: 1;
  padding: 5px;
}

.button-cover,
.knobs,
.layer {
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
}

.button {
  position: relative;
  top: 50%;
  width: 74px;
  height: 36px;
  margin: -20px auto 0 auto;
  overflow: hidden;
}

.checkbox {
  position: relative;
  width: 100%;
  height: 100%;
  padding: 0;
  margin: 0;
  opacity: 0;
  cursor: pointer;
  z-index: 3;
}

.knobs {
  z-index: 2;
}

.layer {
  width: 100%;
  background-color: #ebf7fc;
  transition: 0.3s ease all;
  z-index: 1;
}

.button.r,
.button.r .layer {
  border-radius: 100px;
}

#button-3 .knobs:before {
  content: 'EN';
  position: absolute;
  top: 3px;
  left: 4px;
  width: 30px;
  height: 30px;
  color: #fff;
  font-size: 10px;
  font-weight: bold;
  text-align: center;
  line-height: 1;
  padding: 9px 4px;
  background-color: #03a9f4;
  border-radius: 50%;
  transition: 0.3s ease all, left 0.3s cubic-bezier(0.18, 0.89, 0.35, 1.15);
}

#button-3 .checkbox:active + .knobs:before {
  width: 46px;
  border-radius: 100px;
}

#button-3 .checkbox:checked:active + .knobs:before {
  margin-left: -26px;
}

#button-3 .checkbox:checked + .knobs:before {
  content: 'JP';
  left: 42px;
  background-color: #f44336;
}

#button-3 .checkbox:checked ~ .layer {
  background-color: #fcebeb;
}

.tooltip {
  position: absolute;
  background-color: #000;
  color: #fff;
  padding: 5px;
  border-radius: 5px;
  top: 100%;
  left: 0;
  transform: translateY(5px);
}

span {
  cursor: pointer;
  position: relative;
}

.size-icon {
  font-size: xx-large;
}

.search-input {
  height: 3.75em;
}

.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
