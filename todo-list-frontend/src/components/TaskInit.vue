<script>
import TaskService from '../service/TaskService'
import TaskCreate from './modal/TaskCreate.vue'
import TaskUpdate from './modal/TaskUpdate.vue'
import TaskDelete from './modal/TaskDelete.vue'
// import TaskView from './modal/TaskView.vue'

export default {
  name: 'App',
  components: {
    TaskCreate,
    TaskUpdate,
    TaskDelete,
    // TaskView
  },
  taskName: '',
  data() {
    return {
      taskId: '',
      tasks: [],
      searchInput: '',
      selectedTask: null, // To hold the task selected for viewing
      showViewModal: false,
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
    viewModalCheck() {
      return this.showViewModal;
    }
  },
  methods: {
    findTasks() {
      TaskService.findTasks().then((response) => {
        console.log(response)
        this.tasks = response.data
      })
    },
    handleTaskUpdated(updatedTask) {
      console.log('Updated Task:', updatedTask);
      this.findTasks(); 
    },
    handleTaskCreated(createdTask) {
      console.log('Created Task:', createdTask);
      this.findTasks();
    },
    handleTaskDeleted(response) {
      console.log(response.status);
      //TODO: Add notification
      this.findTasks();
    },
    handleTaskViewed(task) {
      this.showViewModal = !this.showViewModal;
        // Set selectedTask when a row is clicked
        this.selectedTask = task;
        // this.modalActive = !this.modalActive;
        console.log("handleTaskViewed: ");
        console.log(this.showViewModal);
    },
    closeModal() {
      this.showViewModal = false; 
      console.log('closeModal: ' + this.showViewModal);
    },
    // handleTaskUpdateViewed(task) {
    //   // This method is called when clicking on TaskUpdate
    //   // It prevents the TaskView modal from being triggered
    //   this.selectedTask = task;
    //   console.log("task update viewed"+ this.selectedTask);
    // },
    // handleTaskDeleteViewed(task) {
    //   // This method is called when clicking on TaskDelete
    //   // It prevents the TaskView modal from being triggered
    //   this.selectedTask = task;
    //   console.log("task delete viewed"+ this.selectedTask);
    // }
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
        <TaskCreate @task-created="handleTaskCreated" />
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
          <tr @click.stop="handleTaskViewed(task)" v-for="task in filteredTasks" :key="task.id">
            <td>{{ task.id }}</td>
            <td>{{ task.title }}</td>
            <td>{{ task.description }}</td>
            <td>{{ task.start_date }}</td>
            <td>{{ task.end_date }}</td>
            <td>{{ task.status }}</td>
            <td>
              <TaskUpdate :selectedTask="task" @task-updated="handleTaskUpdated" @open-view="handleTaskUpdateViewed" />
            </td>
            <td>
              <TaskDelete :taskId="task.id" @task-deleted="handleTaskDeleted" @open-view="handleTaskDeleteViewed" />
            </td>
          </tr>
        </tbody>
      </table>
      <!-- <TaskView :viewModalCheck="viewModalCheck" :selectedTask="selectedTask" @task-viewed="handleTaskViewed" @close-modal="closeModal" /> -->
    </div>
  </div>
</template>

<style>
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
</style>
