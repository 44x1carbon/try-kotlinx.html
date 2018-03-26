package views.templates

import io.ktor.html.Placeholder
import io.ktor.html.Template
import io.ktor.html.insert
import kotlinx.html.*

class SectionLayout: Template<HTML> {
    val title = Placeholder<TITLE>()
    val sidebar = Section<BODY>()
    override fun HTML.apply() {
        head {
            title {
                +"アプリ名 - "
                insert(this@SectionLayout.title)
            }
        }
        body {
            insertWithParent(sidebar) {
                +"ここがメインのサイドバー"
            }
        }
    }
}


fun <TOuter> TOuter.insertWithParent(section: Section<TOuter>, parent: TOuter.() -> Unit): Unit = section.apply{ this.parent = parent }.apply(this)

class Section<TOuter> {
    private var content: TOuter.(Section<TOuter>) -> Unit = {}
    var meta: String = ""
    var parent: TOuter.() -> Unit = {}

    operator fun invoke(meta: String = "", content: TOuter.(Section<TOuter>) -> Unit) {
        this.content = content
        this.meta = meta
    }

    fun apply(destination: TOuter) {
        destination.content(this)
    }
}