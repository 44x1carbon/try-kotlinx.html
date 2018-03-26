import io.ktor.application.*
import io.ktor.html.respondHtml
import io.ktor.html.respondHtmlTemplate
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.html.*
import views.templates.ComponentLayout
import views.templates.MainViewLayout
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
            get("/components-and-slots") {
                call.respondHtmlTemplate(ComponentLayout()) {}
            }
            get("/sub_views") {
                call.respondHtmlTemplate(MainViewLayout()) {}
            }
        }
    }
    server.start(wait = true)
}