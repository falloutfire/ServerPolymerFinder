package com.kleknerpolymer.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletResponse


@SpringBootApplication
class ServerApplication

fun main(args: Array<String>) {
	runApplication<ServerApplication>(*args)
}

@Component
class SimpleCORSFilter : Filter {
	@Throws(IOException::class, ServletException::class)
	override fun doFilter(req: ServletRequest?, res: ServletResponse, chain: FilterChain) {
		val response = res as HttpServletResponse
		response.setHeader("Access-Control-Allow-Origin", "*")
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT")
		response.setHeader("Access-Control-Max-Age", "3600")
		response.setHeader("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Origin,Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers")
		chain.doFilter(req, res)
	}

	override fun init(filterConfig: FilterConfig?) {}
	override fun destroy() {}
}