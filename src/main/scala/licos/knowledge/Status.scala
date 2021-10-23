package licos.knowledge

sealed abstract class Status(val label: String) extends Product with Serializable {

  override def toString: String = label

  def toIntermediateStatus: Status
  def isAlive: Boolean = {
    import cats.implicits.*
    this.label === Alive.label
  }
  def isDead: Boolean = !isAlive

  override def equals(o: Any): Boolean = {
    import cats.implicits.*
    o match {
      case status: Status if status.label === this.label => true
      case _ => false
    }
  }
}

case object Alive extends Status("alive") {
  override def hashCode():           Int    = 514001
  override def toIntermediateStatus: Status = this
}

case object Dead extends Status("dead") {
  override def hashCode():           Int    = 514002
  override def toIntermediateStatus: Status = this
}

case object DeathByExecution extends Status("death by execution") {
  override def hashCode():           Int    = 514003
  override def toIntermediateStatus: Status = this
}

case object DeathByAttack extends Status("death by attack") {
  override def hashCode():           Int    = 514004
  override def toIntermediateStatus: Status = Dead
}

case object DeathByFear extends Status("death by fear") {
  override def hashCode():           Int    = 514005
  override def toIntermediateStatus: Status = Dead
}

case object UnnaturalDeath extends Status("unnatural death") {
  override def hashCode():           Int    = 514006
  override def toIntermediateStatus: Status = UnnaturalDeath
}
