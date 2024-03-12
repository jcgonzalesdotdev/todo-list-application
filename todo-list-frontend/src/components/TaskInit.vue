<script>
import TaskService from '../service/TaskService'
import HeaderSection from './landing-page/header-section.vue'

export default {
    name: 'App',
    components: {
      HeaderSection
    },
  taskName: '',
  data() {
    return {
      tasks: []
    }
  },
  methods: {
    findTasks() {
      TaskService.findTasks().then((response) => {
        this.tasks = response.data
      })
    }
  },
  created() {
    this.findTasks()
  }
}
</script>

<template>

  <div id="app">
    <HeaderSection />
  </div>

  <div class="container">
    <div class="tbl-container table-responsive bdr">
      <table class="table table-hover">
        <thead class="thead-dark">
          <tr class="table-primary">
            <th scope="col">タスクID</th>
            <th scope="col">タイトル</th>
            <th scope="col">内容</th>
            <th scope="col">開始日</th>
            <th scope="col">終了日</th>
            <th scope="col">ステータス</th>
            <th scope="col"></th>
            <th scope="col"></th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="task in tasks" :key="task.id">
            <td>{{ task.id }}</td>
            <td>{{ task.title }}</td>
            <td>{{ task.description }}</td>
            <td>{{ task.start_date }}</td>
            <td>{{ task.end_date }}</td>
            <td>{{ task.status }}</td>
            <td>
              <button>更新</button>
            </td>
            <td>
              <button>削除</button>
            </td>
          </tr>
        </tbody>
      </table>
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
</style>
