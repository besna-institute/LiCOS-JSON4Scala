package licos.knowledge

sealed abstract class Directionality(val label: String) extends Product with Serializable {
  override def toString: String = label
}

case object ServerToClient extends Directionality("server to client")
case object ClientToServer extends Directionality("client to server")
