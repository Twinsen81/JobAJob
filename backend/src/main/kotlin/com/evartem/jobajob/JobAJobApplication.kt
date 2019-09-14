package com.evartem.jobajob

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class JobAJobApplication

fun main(args: Array<String>) {
	runApplication<JobAJobApplication>(*args)
}

@RestController
class HelloController {
	@GetMapping("/hello")
	fun hello(name: String) =
			"Hello again3, $name!"
}
