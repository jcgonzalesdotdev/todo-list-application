import axios from "axios";
import http from "@/http-common";
import { API_BASE_URL } from '@/utils/constants';

class TaskService{
    findTasks(){
        return http.get('/find');
    }
    deleteTask(taskId){
        // return axios.delete(API_BASE_URL + '/delete/', taskId);
        return http.delete('/delete/' + taskId);
    }
    createTask(taskData){
        return http.post('/add', taskData);
    }
    updateTask(taskId, taskData) {
        return http.put('/update/' + taskId, taskData);
    }
}

export default new TaskService()