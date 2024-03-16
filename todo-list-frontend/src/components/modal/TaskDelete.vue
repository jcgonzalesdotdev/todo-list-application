<template>
    <div>
      <button class="m-2" @click="modalCheck">削除</button>
      <TaskModal :modalActive="isModalActive" @close-modal="modalCheck">
        <div>
          <p>Are you sure you want to delete this task?</p>
          <div class="form-button">
            <button class="button-save" @click="deleteTask">Delete</button>
            <button class="button-cancel" @click="modalCheck">Cancel</button>
          </div>
        </div>
      </TaskModal>
    </div>
  </template>
  
  <script>
  import TaskService from '@/service/TaskService';
  import TaskModal from './TaskModal.vue';
  
  export default {
    components: {
      TaskModal
    },
    props: ['taskId'], // Receive task ID from parent component
    data() {
      return {
        modalActive: true
      };
    },
    computed: {
      isModalActive() {
        return !this.modalActive;
      }
    },
    methods: {
      modalCheck() {
        this.modalActive = !this.modalActive;
      },
      async deleteTask() {
        try {
          await TaskService.deleteTask(this.formData.id).then((response) => {
              this.$emit('task-deleted', response.data); // Emit event to parent component
              this.modalActive =  !this.modalActive; 
          });
        } catch (error) {
          console.error('Error deleting task:', error);
        }
      }
    },
    watch: {
    taskId: {
      handler(newVal) {
        // Update formData when selectedTask changes
        this.formData = { ...newVal };
      },
      immediate: true // Trigger on component mount
    }
  }
  };
  </script>

<style>
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
  