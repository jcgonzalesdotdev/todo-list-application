import axios from 'axios'

const TASK_API_BASE_URL = 'http://localhost:8080/api/tasks'

class TaskService{
    findTasks(){
        return axios.get(TASK_API_BASE_URL + '/find');
    }
}

export default new TaskService()