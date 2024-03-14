<script>
import TaskService from '../service/TaskService'
import HeaderSection from './landing-page/header-section.vue'
import TaskModal from './modal/TaskModal.vue'

export default {
    name: 'App',
    components: {
      HeaderSection,
      TaskModal,
    },
  taskName: '',
  data() {
    return {
      tasks: [],
      formData: {
        title: '',
        description: '',
        startDate: '',
        endDate: ''
      }
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
const modalActive = ref(null);
const toggleModal
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
      <TaskModal>
        <div class="modal">
          <div class="modal-content">
            <form action="" method="post">
              <div class="form-input">
                    <label>Title:</label>
                    <input v-model="formData.title" type="text" placeholder="ex. Development Task" class="input-title" required>
                    <label>Description:</label>
                    <textarea v-model="formData.description" placeholder="Write your description here" class="input-desc" required></textarea>
                    <label>Start Date:</label>
                    <input v-model="formData.startDate" type="date" placeholder="Start Date" class="input-startD" required>
                    <label>End Date:</label>
                    <input v-model="formData.endDate" type="date" placeholder="End Date" class="input-endD" required>
                </div>
                <div class="form-button">
                    <button class="button-save" type="submit">Save</button>
                    <button class="button-cancel" type="reset" @click="cancelEnterRecord">Cancel</button>
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
/* Modal Style */
    /* Styles for the modal */
    .modal {
        display: block;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
    }
    
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
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
        color: #FFFFFF;
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
        background-color: #BDDCE3;
        box-shadow: inset 2px 5px 10px rgba(0,0,0,0.3);
        transition: 300ms ease-in-out;
    }

    .input-title:focus,
    .input-desc:focus,
    .input-startD:focus,
    .input-endD:focus {
        background-color: white;
        transform: scale(1.05);
        box-shadow: 13px 13px 100px #969696,
                    -13px -13px 100px #ffffff;
    }

    textarea{
        height: 100px;
        resize: none;
    }
</style>