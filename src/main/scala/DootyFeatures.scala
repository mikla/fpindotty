
enum Color {
  case Red, Green, Blue
}

enum Option[+T] {
  case Some[T](value: T)
  case None
}

trait WithX(x: Int)

case class Person(name: String)
case class Viewers(persons: List[Person])
case class Paper(label: String)

class Table {
  def add(row: Row): Unit = {}
}
class Cell(str: String)
class Row {
  def add(cell: Cell): Unit = {}
}

object DottyFeatures extends App {

    // Enums
    println("Dotty features")
    val x: Color = Color.Red
    println(x)

    val y: Option[Int] = Option.Some(1)
    println(y)

    // Dealing with Context
    implicit val viewers: Viewers = Viewers(List(Person("Person")))
    type ScoreType = implicit Viewers => Int
    def score(paper: Paper): ScoreType = 0
    println(score(Paper("Paper")))

    // builder pattern / receiver functions
    def cell(str: String)(implicit r: Row) =
      r.add(new Cell(str))

    def row(init: implicit Row => Unit)(implicit t: Table) = {
      implicit val r = new Row
      init
      t.add(r)
    }

    def table(init: implicit Table => Unit) = {
      implicit val t = new Table
      init
      t
    }

    table {
      row {
        cell("fdfd")
      }
      row {
        cell("fdsf")
      }
    }

}
