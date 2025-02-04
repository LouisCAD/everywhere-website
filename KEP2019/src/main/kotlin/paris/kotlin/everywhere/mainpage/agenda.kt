package paris.kotlin.everywhere.mainpage


import kotlinx.css.*
import kotlinx.css.properties.border
import kotlinx.css.properties.boxShadow
import kotlinx.html.P
import paris.kotlin.everywhere.utils.getValue
import react.*
import react.dom.RDOMBuilder
import react.dom.div
import react.dom.h3
import react.dom.p
import styled.css
import styled.styledDiv

private fun CSSBuilder.track(big: Boolean) {
    width = if (big) 40.pct else 70.pct
    position = Position.absolute
    top = 0.px
    bottom = 0.px
    border(2.px, BorderStyle.solid, Color.silver)
    color = Color.silver
    borderRadius = 0.5.em

    "h3" {
        textAlign = TextAlign.center
        marginTop = 0.5.em
    }
}

private fun CSSBuilder.entryLine(big: Boolean) {
    display = Display.flex
    flexDirection = FlexDirection.row
    alignItems = Align.center
    justifyContent = JustifyContent.flexEnd
    margin(1.em, 0.em)

    "h3" {
        color = Color.white
        textAlign = TextAlign.left
        flexGrow = 1.0
    }

    "p" {
        textAlign = TextAlign.center
        padding(1.em)
    }

    "div.entry" {
        boxShadow(Color.black, blurRadius = 3.px)
        borderRadius = 0.4.em
        zIndex = 1
        backgroundColor = Color.whiteSmoke

        + "event" {
            width = if (big) 88.pct else 76.pct
        }

        + "talk" {
            width = 36.pct
            margin(0.px, 4.pct, 0.em, 4.pct)

            + "small" {
                width = 64.pct
                margin(0.px, 6.pct, 0.em, 6.pct)
            }
        }
    }

    "div.empty" {
        width = 36.pct
        margin(0.px, 4.pct, 0.em, 4.pct)

        + "small" {
            width = 64.pct
            margin(0.px, 6.pct, 0.em, 6.pct)
        }
    }

}

private fun RBuilder.event(big: Boolean, time: String, inside: RDOMBuilder<P>.() -> Unit) {
    styledDiv {
        css {
            entryLine(big)
        }

        h3 {
            +time
        }

        div("entry event") {
            p {
                inside()
            }
        }
    }
}

private fun RBuilder.talk(time: String, talk: RDOMBuilder<P>.() -> Unit) {
    styledDiv {
        css {
            entryLine(false)
        }

        h3 {
            +time
        }

        div("entry talk small") {
            p {
                talk()
            }
        }
    }
}

private fun RBuilder.talks(time: String, talk1: RDOMBuilder<P>.() -> Unit, talk2: (RDOMBuilder<P>.() -> Unit)? = null) {
    styledDiv {
        css {
            entryLine(true)
        }

        h3 {
            +time
        }

        div("entry talk") {
            p {
                talk1()
            }
        }

        if (talk2 == null) {
            div("empty") {}
        } else {
            div("entry talk") {
                p {
                    talk2()
                }
            }
        }
    }
}

val agenda by functionalComponent<RProps> {
    styledDiv {
        css {
            margin(1.em)
        }
        styledDiv {
            css {
                maxWidth = 51.em
                position = Position.relative
                margin(20.px, LinearDimension.auto, 60.px, LinearDimension.auto)
                paddingTop = 2.em
                paddingBottom = 0.2.em

                media("(max-width: 700px)") {
                    display = Display.none
                }
            }

            styledDiv {
                css {
                    track(true)
                    right = 46.pct
                }
                h3 {
                    +"Amphithéatre"
                }
            }

            styledDiv {
                css {
                    track(true)
                    right = 2.pct
                }
                h3 {
                    +"Salle 1"
                }
            }

            event(true, "13h00") {
                +"Accueil, networking & rafraichissements"
            }

            talks(
                "14h00",
                { +"Ouverture" }
            )

            talks(
                "14h30",
                { +"Conférence" },
                { +"Conférence" }
            )

            talks(
                "15h30",
                { +"Conférence" },
                { +"Conférence" }
            )

            event(true, "16h30") {
                +"Networking & rafraichissements"
            }

            talks(
                "17h00",
                { +"Conférence" },
                { +"Conférence" }
            )

            talks(
                "18h00",
                { +"Conférence" },
                { +"Conférence" }
            )

            talks(
                "19h00",
                { +"Clôture" }
            )

            event(true, "19h30") {
                +"Restauration, networking & rafraichissements"
            }
        }

        styledDiv {
            css {
                maxWidth = 28.em
                position = Position.relative
                margin(20.px, LinearDimension.auto, 60.px, LinearDimension.auto)
                paddingTop = 2.em
                paddingBottom = 0.2.em

                media("(min-width: 700px)") {
                    display = Display.none
                }
            }

            styledDiv {
                css {
                    track(false)
                    right = 3.pct
                }
                h3 {
                    +"Amphithéatre"
                }
            }

            event(false, "13h00") { +"Accueil, networking & rafraichissements" }

            talk("14h00") { +"Ouverture" }

            talk("14h30") { +"Conférence" }

            talk("15h30") { +"Conférence" }

            event(false, "16h30") {
                +"Networking & rafraichissements !"
            }

            talk("17h00") { +"Conférence" }

            talk("18h00") { +"Conférence" }

            talk("19h00") { +"Clôture" }

            event(false, "19h30") { +"Restauration, networking & rafraichissements" }
        }

        styledDiv {
            css {
                maxWidth = 28.em
                position = Position.relative
                margin(20.px, LinearDimension.auto, 60.px, LinearDimension.auto)
                paddingTop = 2.em
                paddingBottom = 0.2.em

                media("(min-width: 700px)") {
                    display = Display.none
                }
            }

            styledDiv {
                css {
                    track(false)
                    right = 3.pct
                }
                h3 {
                    +"Salle 1"
                }
            }

            talk("14h30") { +"Conférence" }

            talk("15h30") { +"Conférence" }

            event(false, "16h30") {
                +"Networking & rafraichissements !"
            }

            talk("17h00") { +"Conférence" }

            talk("18h00") { +"Conférence" }
        }

    }
}
