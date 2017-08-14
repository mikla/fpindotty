
trait BazProps {
  def baz: String
}

trait BarProps {
  def bar: String
}

trait CommonProps {
  def common: String 
}

class CommonBarProps extends CommonProps with BarProps {
  def common: String = "common"
  def bar: String = "bar"
}

object UnionTypes extends App {
  
  type Props = CommonProps & (BarProps | BazProps)
  
  val f: Props = new CommonBarProps
    
  println(f.common) // ok
  
  // value `baz` is not a member of UnionTypes.Props
  // println(f.baz)  
}