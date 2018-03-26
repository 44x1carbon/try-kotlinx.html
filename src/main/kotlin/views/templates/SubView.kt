package views.templates

import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.*

class SubView<in TOuter: HtmlBlockTag>(val some: String): Template<TOuter> {
    override fun TOuter.apply() {
        div {
            +"Hello $some"
        }
    }
}

class MainViewLayout: Template<HTML> {
    override fun HTML.apply() {
        body {
            insert(SubView("data")) {}
        }
    }

}