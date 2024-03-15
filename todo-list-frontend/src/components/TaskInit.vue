<script>
import TaskService from '../service/TaskService'
import TaskCreate from '../components/TaskCreate.vue'
import TaskModal from './modal/TaskModal.vue'

export default {
  name: 'App',
  components: {
    TaskCreate,
    TaskModal
  },
  taskName: '',
  data() {
    return {
      taskId: '',
      tasks: [],
      searchInput: '',
      formData: {
        title: '',
        description: '',
        start_date: '',
        end_date: '',
        status: ''
      },
      modalActive: true,
      isUpdateMode: false // Flag to indicate whether it's update mode or not
    }
  },
  computed: {
    filteredTasks() {
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
        return (
          title.includes(this.searchInput.toLowerCase()) ||
          description.includes(this.searchInput.toLowerCase()) ||
          start_date.includes(this.searchInput.toLowerCase()) ||
          end_date.includes(this.searchInput.toLowerCase()) ||
          status.includes(this.searchInput.toLowerCase())
        )
      })
    },
    modalActive() {
      return !this.modalActive
    }
  },
  methods: {
    findTasks() {
      TaskService.findTasks().then((response) => {
        console.log(response)
        this.tasks = response.data
      })
    },
    async deleteSelectTaskById(taskId) {
      this.taskId = taskId
      let selectedTaskId = BigInt(taskId)
      await TaskService.deleteTask(selectedTaskId).then((response) => {
        this.findTasks()
      })
    },
    modalCheck(task) {
      if (!task) {
        this.isUpdateMode = false; // Set create mode
      }
      console.log(this.isUpdateMode);
      this.modalActive = !this.modalActive // Open modal
    },
    updateModalCheck(task) {
      if (task) {
        this.formData = { ...task }; // Copy task data to formData
        this.isUpdateMode = true; // Set update mode
      }
      console.log(this.isUpdateMode);
      this.modalActive = !this.modalActive // Open modal
    },
    async createOrUpdateTask() {
      if (this.isUpdateMode) {
        // Update existing task
        await TaskService.updateTask(this.formData.id, this.formData);
        
      } else {
        // Create new task
        await TaskService.createTask(this.formData).then((response) =>{
          this.findTasks();
          this.modalActive = !this.modalActive // Close modal
        });
      }
    }
  },
  created() {
    this.findTasks()
  }
}
</script>

<template>
  <div class="container">
    <div class="tbl-container table-responsive bdr">
      <div class="mx-auto d-lg-flex">
          <input
            class="form-control mr-sm-2 m-2"
            type="text"
            placeholder="検索"
            aria-label="Search"
            v-model="searchInput"
          />
          <!-- <TaskCreate class="m-2" @click="modalCheck" a /> -->
          <button class="m-2" @click="modalCheck">作成</button>
        </div>
      <table class="table table-hover">
        <thead class="thead-dark">
          <tr class="table-primary">
            <th scope="col">タスクID</th>
            <th scope="col">タイトル</th>
            <th scope="col">詳細</th>
            <th scope="col">開始日</th>
            <th scope="col">終了日</th>
            <th scope="col">ステータス</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in filteredTasks" :key="task.id">
            <td>{{ task.id }}</td>
            <td>{{ task.title }}</td>
            <td>{{ task.description }}</td>
            <td>{{ task.start_date }}</td>
            <td>{{ task.end_date }}</td>
            <td>{{ task.status }}</td>
            <td>
              <button @click="updateModalCheck(task)">更新</button>
            </td>
            <td>
              <button @click="deleteSelectTaskById(task.id)">削除</button>
            </td>
          </tr>
        </tbody>
      </table>
      <TaskModal :modalActive="modalActive" @close-modal="modalCheck">
        <div class="modal">
          <div class="modal-content">
            <form action="" method="post">
              <div class="form-input">
                <label>Title:</label>
                <input
                  v-model="formData.title"
                  type="text"
                  placeholder="ex. Development Task"
                  class="input-title"
                />
                <label>Description:</label>
                <textarea
                  v-model="formData.description"
                  placeholder="Write your description here"
                  class="input-desc"
                ></textarea>
                <label>Start Date:</label>
                <input
                  v-model="formData.start_date"
                  type="date"
                  placeholder="Start Date"
                  class="input-startD"
                />
                <label>End Date:</label>
                <input
                  v-model="formData.end_date"
                  type="date"
                  placeholder="End Date"
                  class="input-endD"
                />
              </div>
              <!-- status checkbox -->
              <div class="checkbox-wrapper">
                <input checked="" type="checkbox" class="check" id="check1-61" v-model="formData.status" />
                <label for="check1-61" class="label">
                  <svg width="45" height="45" viewBox="0 0 95 95">
                    <rect x="30" y="20" width="50" height="50" stroke="black" fill="none"></rect>
                    <g transform="translate(0,-952.36222)">
                      <path
                        d="m 56,963 c -102,122 6,9 7,9 17,-5 -66,69 -38,52 122,-77 -7,14 18,4 29,-11 45,-43 23,-4"
                        stroke="black"
                        stroke-width="3"
                        fill="none"
                        class="path1"
                      ></path>
                    </g>
                  </svg>
                  <span>Task Completed?</span>
                </label>
              </div>
              <div class="form-button">
                <button class="button-save" type="submit" @click="createOrUpdateTask">Save</button>
                <button class="button-cancel" type="reset" @click="modalCheck">Cancel</button>
              </div>
            </form>
          </div>
        </div>
      </TaskModal>
    </div>
  </div>
</template>

<style scoped>
.container {
  position: relative;
  z-index: 2;
}

button {
  appearance: none;
  background-color: transparent;
  border: 0.125em solid #1a1a1a;
  border-radius: 0.9375em !important;
  box-sizing: border-box;
  color: #3b3b3b;
  cursor: pointer;
  display: inline-block;
  font-family:
    Roobert,
    -apple-system,
    BlinkMacSystemFont,
    'Segoe UI',
    Helvetica,
    Arial,
    sans-serif,
    'Apple Color Emoji',
    'Segoe UI Emoji',
    'Segoe UI Symbol';
  font-size: 12px !important;
  font-weight: 600;
  line-height: normal;
  margin: 0;
  min-height: 3.75em;
  min-width: 0;
  outline: none;
  padding: 1em 2.3em;
  text-align: center;
  text-decoration: none;
  transition: all 300ms cubic-bezier(0.23, 1, 0.32, 1);
  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
  will-change: transform;
}

button:disabled {
  pointer-events: none;
}

button:hover {
  color: #fff;
  background-color: #1a1a1a;
  box-shadow: rgba(0, 0, 0, 0.25) 0 8px 15px;
  transform: translateY(-2px);
}

button:active {
  box-shadow: none;
  transform: translateY(0);
}

table {
  margin-top: 2em;
  font-family: monospace;
}

.bdr {
  border-radius: 6px;
  overflow: hidden;
}

/* Styles for the modal */
.modal {
  display: block;
  position: fixed;
  z-index: 1;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: hidden;
  background-color: rgba(0, 0, 0, 0.4);
}

.modal-content {
  background-color: #fefefe;
  margin: 5% auto;
  padding: 20px;
  border: 1px solid #888;
  width: 20%;
  color: black;
}

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

.form-input {
  display: flex;
  flex-direction: column;
  padding: 10px;
  width: 100%;
}

.form-button {
  display: flex;
  flex-direction: row;
  justify-content: center;
  padding: 10px;
}

.button-save,
.button-cancel {
  margin: 5px;
  color: #ffffff;
  border-radius: 5px;
  border: 1px solid transparent;
}

.button-save {
  background-color: cornflowerblue;
}

.button-cancel {
  background-color: crimson;
}

.input-title,
.input-desc,
.input-startD,
.input-endD {
  border: none;
  outline: none;
  border-radius: 10px;
  padding: 0.3rem;
  margin: 0.1rem;
  background-color: #bddce3;
  box-shadow: inset 2px 5px 10px rgba(0, 0, 0, 0.3);
  transition: 300ms ease-in-out;
}

.input-title:focus,
.input-desc:focus,
.input-startD:focus,
.input-endD:focus {
  background-color: white;
  transform: scale(1.05);
  box-shadow:
    13px 13px 100px #969696,
    -13px -13px 100px #ffffff;
}

textarea {
  height: 100px;
  resize: none;
}

/* Status Checkbox */
.checkbox-wrapper input[type="checkbox"] {
  visibility: hidden;
  display: none;
}

.checkbox-wrapper *,
  .checkbox-wrapper ::after,
  .checkbox-wrapper ::before {
  box-sizing: border-box;
  user-select: none;
}

.checkbox-wrapper {
  position: relative;
  display: block;
  overflow: hidden;
}

.checkbox-wrapper .label {
  cursor: pointer;
}

.checkbox-wrapper .check {
  width: 50px;
  height: 50px;
  position: absolute;
  opacity: 0;
}

.checkbox-wrapper .label svg {
  vertical-align: middle;
}

.checkbox-wrapper .path1 {
  stroke-dasharray: 400;
  stroke-dashoffset: 400;
  transition: .5s stroke-dashoffset;
  opacity: 0;
}

.checkbox-wrapper .check:checked + label svg g path {
  stroke-dashoffset: 0;
  opacity: 1;
}
</style>
