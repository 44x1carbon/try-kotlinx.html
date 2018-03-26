import io.ktor.application.*
import io.ktor.html.respondHtml
import io.ktor.html.respondHtmlTemplate
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import views.templates.SectionLayout

fun main(args: Array<String>) {
    val server = embeddedServer(Netty, port = 8080) {
        routing {
            get("/") {
                call.respondHtmlTemplate(SectionLayout()) {
                    title {
                        +"Page Title"
                    }
                    sidebar { section ->
                        section.parent(this)
                        p { +"ここはメインのサイドバーに追加される" }
                    }
                }
            }
            get("/demo") {
                call.respondText("HELLO WORLD!")
            }
        }
    }
    server.start(wait = true)
}