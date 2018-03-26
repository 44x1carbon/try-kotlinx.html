package views.templates

import io.ktor.html.Template
import kotlinx.html.*

fun HtmlBlockTag.alert(slot: DIV.() -> Unit) {
    div(classes = "alert alert-danger") {
        slot()
    }
}

class ComponentLayout: Template<HTML> {
    override fun HTML.apply() {
        head {
            title { +"アプリ名 - " }
        }
        body {
            alert {
                strong { +"Whoops!" }
                +"Something went wrong!"
            }
        }
    }
}