<template>
  <TaskModal :modalActive="viewModalCheck" @close-modal="$emit('close-modal')">
    <div>
      <h1 class="title">{{ formData.title }}</h1>
      <p><strong>Description:</strong> {{ formData.description }}</p>
      <p><strong>Start Date:</strong> {{ formData.start_date }}</p>
      <p><strong>End Date:</strong> {{ formData.end_date }}</p>
      <p><strong>Status:</strong> {{ formData.status }}</p>
    </div>
    <div class="form-button">
      <button class="button-cancel" type="reset" @click="$emit('close-modal')">Close</button>
    </div>
  </TaskModal>
</template>

<script>
import TaskModal from './TaskModal.vue'
import { COMMON_UTILS } from '@/utils/common'

export default {
  components: {
    TaskModal
  },
  // Props receive selected task from parent component
  props: ['viewModalCheck', 'selectedTask'],
  data() {
    return {
      formData: {
        title: '',
        description: '',
        start_date: '',
        end_date: '',
        status: ''
      },
      viewDateFormatCheck: false
    }
  },
  watch: {
    selectedTask: {
      handler(newVal) {
        this.formData.id = newVal.id
        this.formData.title = newVal.title
        this.formData.description = newVal.description
        this.formData.start_date = newVal.start_date
        this.formData.end_date = newVal.end_date
        this.formData.status = newVal.status
      }
    },
    dateFormatCheck: {
      handler(newVal) {
        this.selectedTask()
      }
    }
  }
}
</script>
