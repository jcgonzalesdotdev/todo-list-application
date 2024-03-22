<template>
  <div>
    <button class="m-2" @click.stop="modalCheck"><i class="fa-solid fa-pen-to-square"></i></button>
    <TaskModal :modalActive="isModalActive" @close-modal="modalCheck">
      <form>
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
          <input type="checkbox" class="check" id="check1-61" v-model="formData.status" />
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
          <button class="button-save" @click.prevent="updateTask">Update</button>
          <button class="button-cancel" @click="modalCheck">Cancel</button>
        </div>
      </form>
    </TaskModal>
  </div>
</template>

<script>
import TaskService from '@/service/TaskService'
import TaskModal from './TaskModal.vue'
import { stringToDate } from '@/utils/common'

export default {
  components: {
    TaskService,
    TaskModal
  },
  props: ['selectedTask'], // Receive selected task from parent component
  data() {
    return {
      formData: {
        title: '',
        description: '',
        start_date: '',
        end_date: '',
        status: ''
      },
      modalActive: true
    }
  },
  computed: {
    isModalActive() {
      return !this.modalActive
    }
  },
  methods: {
    modalCheck() {
      this.modalActive = !this.modalActive
    },
    async updateTask() {
      await TaskService.updateTask(this.formData).then((response) => {
        this.$emit('task-updated', response.data)
        this.modalActive = !this.modalActive
      })
    }
  },
  watch: {
    selectedTask: {
      handler(newVal) {
        // Update formData when selectedTask changes
        let stringStartDate = stringToDate(newVal.start_date)
        let stringEndDate = stringToDate(newVal.end_date)

        this.formData.id = newVal.id
        this.formData.title = newVal.title
        this.formData.description = newVal.description
        this.formData.start_date = stringStartDate
        this.formData.end_date = stringEndDate
        this.formData.status = newVal.status
      },
      immediate: true // Trigger on component mount
    }
  }
}
</script>

<style>
/* styles from the form can be transferred inside the TaskModal. Para isang tawagan nalang??? */
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
  box-shadow: 13px 13px 100px #969696, -13px -13px 100px #ffffff;
}

textarea {
  height: 100px;
  resize: none;
}

/* Status Checkbox */
.checkbox-wrapper input[type='checkbox'] {
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
  transition: 0.5s stroke-dashoffset;
  opacity: 0;
}

.checkbox-wrapper .check:checked + label svg g path {
  stroke-dashoffset: 0;
  opacity: 1;
}
</style>
