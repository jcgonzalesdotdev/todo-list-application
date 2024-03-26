import http from "@/http-common";

class TaskService{
    init(){
        return http.get('/find');
    }
    deleteTask(taskId){
        return http.delete('/delete/' + taskId);
    }
    createTask(taskData){
        return http.post('/add', taskData);
    }
    updateTask(taskData) {
        return http.put('/update/' + taskData.id, taskData);
    }
}

export default new TaskService()