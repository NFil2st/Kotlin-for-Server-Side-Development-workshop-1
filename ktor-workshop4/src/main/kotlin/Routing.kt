package com.role

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Task(val id: Int,val content: String,val isDone: Boolean)

@Serializable
data class TaskRequest(val content: String,val isDone: Boolean)

object TaskRepository {
    private val tasks = mutableListOf<Task>(
        Task(id = 1, content = "Learn Ktor", isDone = true),
        Task(id = 2, content = "Build a REST API", isDone = false),
        Task(id = 3, content = "Write Unit Tests", isDone = false)
    )
    private var nextId = 4

    fun getAll(): List<Task> = tasks

    fun getById(id: Int): Task? = tasks.find { it.id == id }

    fun add(taskRequest: TaskRequest): Task {
        val task = Task(id = nextId++, content = taskRequest.content, isDone = taskRequest.isDone)
        tasks.add(task)
        return task
    }

    fun update(id: Int, updatedTask: Task): Boolean {
        val index = tasks.indexOfFirst { it.id == id }
        return if (index != -1) {
            tasks[index] = Task(id, updatedTask.content, updatedTask.isDone)
            true
        } else {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return tasks.removeIf { it.id == id }
    }
}

fun Application.configureRouting() {
    routing {
        get("/tasks") {
            val allTasks = TaskRepository.getAll()
            if (allTasks.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "Not found")
            } else {
                call.respond(HttpStatusCode.OK, allTasks)
            }
        }

        get("/tasks/{id}") {
            val allTasks = TaskRepository.getAll()
            if (allTasks.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "Not found")
            } else {
                val id = call.parameters["id"]?.toIntOrNull()
                if (id == null) {
                    call.respond(HttpStatusCode.BadRequest, "Invalid ID")
                    return@get
                }

                val task = TaskRepository.getById(id)
                if (task != null) {
                    call.respond(task)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task not found")
                }
            }
        }
        post("/tasks") {
            val allTasks = TaskRepository.getAll()
            if (allTasks.isEmpty()) {
                call.respond(HttpStatusCode.NotFound, "Not found")
            }else{
                val taskRequest = call.receive<TaskRequest>()
                val createdTask = TaskRepository.add(taskRequest)
                call.respond(HttpStatusCode.Created, createdTask)
            }
        }

        put("/tasks/{id}") {
            val taskId = call.parameters["id"]?.toIntOrNull()
            if (taskId == null) {
                call.respond(HttpStatusCode.NotFound, "Not found")
            }else{
                val task = TaskRepository.getById(taskId)
                if (task != null) {
                    call.respond(HttpStatusCode.OK, task)
                }
            }
        }
        delete("/tasks/{id}") {
            val taskId = call.parameters["id"]?.toIntOrNull()
            if (taskId == null) {
                call.respond(HttpStatusCode.NotFound, "Not found")
                return@delete
            }else{
                val success = TaskRepository.delete(taskId)
                if (success) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Task not found")
                }
            }
        }
    }
}
