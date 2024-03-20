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
import TaskModal from './TaskModal.vue';

export default {
  components: {
    TaskModal
  },
  // Props receive selected task from parent component
  props: ['viewModalCheck','selectedTask'],
  watch: {
    selectedTask: {
      handler(newVal) {
        // Update formData when selectedTask changes
        this.formData = { ...newVal };
        // Open modal when selectedTask is set
        // this.modalActive = true;
      },
      immediate: true // Trigger on component mount
    }
  }
};
</script>
