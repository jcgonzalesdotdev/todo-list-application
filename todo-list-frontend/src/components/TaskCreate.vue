<script>
import TaskService from '../service/TaskService'

export default {
  data() {
    return {
      taskData: {
        title: 'title new',
        description: 'description new',
        start_date: '2024-03-13',
        end_date: '2024-03-14',
        status: 'COMPLETED'
      }
    }
  },
  methods: {
    // async createTask() {
    //   try {
    //     const response = await fetch('/api/tasks/add', {
    //       method: 'POST',
    //       headers: {
    //         'Content-Type': 'application/json'
    //       },
    //       body: JSON.stringify({
    //         title: this.title,
    //         description: this.description,
    //         start_date: this.start_date,
    //         end_date: this.end_date,
    //         status: this.status
    //       })
    //     })
    //   } catch (error) {
    //     console.error('Error creating task:', error)
    //   }
    // }, 
    async createTask() {
      await TaskService.createTask(this.taskData).then((response) => {
        console.log(response);
      })
    }
  }
}
</script>

<template>
  <button @click="createTask">作成</button>
</template>
