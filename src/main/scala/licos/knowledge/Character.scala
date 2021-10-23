package licos.knowledge

import java.util.Locale

import licos.protocol.element.village.part.NameProtocol
import licos.util.WerewolfWorld

@SuppressWarnings(Array[String]("org.wartremover.warts.Var"))
sealed abstract class Character(val name: NameProtocol) extends Product with Serializable {
  private var id: Int = -1

  def getId: Int = id

  def setId(id: Int): Character = {
    this.id = id
    this
  }

  def icon: String = WerewolfWorld.characterIcon(name.en.head.toString.toLowerCase(Locale.ENGLISH))

  override def equals(o: Any): Boolean = {
    o match {
      case character: Character =>
        import cats.implicits.*
        this.name.en === character.name.en
      case _ => false
    }
  }

  override def toString: String = name.en

}

final case class Adil()
    extends Character(
      NameProtocol()
        .ar("Adil")
        .de("Adil")
        .en("Adil")
        .es("Adil")
        .it("Adil")
        .fr("Adil")
        .ja("アーディル")
        .pt("Adil")
        .ru("Адил")
        .uk("Адил")
        .vi("Adil")
        .zhCN("Adil")
        .zhTW("Adil")
    ) {
  override def hashCode(): Int = 511001
}

final case class Borya()
    extends Character(
      NameProtocol()
        .ar("Borya")
        .de("Borya")
        .en("Borya")
        .es("Borya")
        .it("Borya")
        .fr("Borya")
        .ja("ボーリャ")
        .pt("Borya")
        .ru("Боря")
        .uk("Боря")
        .vi("Borya")
        .zhCN("Borya")
        .zhTW("Borya")
    ) {
  override def hashCode(): Int = 511002
}

final case class Chacha()
    extends Character(
      NameProtocol()
        .ar("Chacha")
        .de("Chacha")
        .en("Chacha")
        .es("Chacha")
        .it("Chacha")
        .fr("Chacha")
        .ja("チャチャ")
        .pt("Chacha")
        .ru("Чача")
        .uk("Чача")
        .vi("Chacha")
        .zhCN("Chacha")
        .zhTW("Chacha")
    ) {
  override def hashCode(): Int = 511003
}

final case class Devdatta()
    extends Character(
      NameProtocol()
        .ar("Devdatta")
        .de("Devdatta")
        .en("Devdatta")
        .es("Devdatta")
        .it("Devdatta")
        .fr("Devdatta")
        .ja("デヴゥダッタ")
        .pt("Devdatta")
        .ru("Девдатта")
        .uk("Девдатта")
        .vi("Devdatta")
        .zhCN("Devdatta")
        .zhTW("Devdatta")
    ) {
  override def hashCode(): Int = 511004
}

final case class Ekrem()
    extends Character(
      NameProtocol()
        .ar("Ekrem")
        .de("Ekrem")
        .en("Ekrem")
        .es("Ekrem")
        .it("Ekrem")
        .fr("Ekrem")
        .ja("エクレム")
        .pt("Ekrem")
        .ru("Екрем")
        .uk("Екрем")
        .vi("Ekrem")
        .zhCN("Ekrem")
        .zhTW("Ekrem")
    ) {
  override def hashCode(): Int = 511005
}

final case class Fernando()
    extends Character(
      NameProtocol()
        .ar("Fernando")
        .de("Fernando")
        .en("Fernando")
        .es("Fernando")
        .it("Fernando")
        .fr("Fernando")
        .ja("フェルナンド")
        .pt("Fernando")
        .ru("Фернандо")
        .uk("Фернандо")
        .vi("Fernando")
        .zhCN("Fernando")
        .zhTW("Fernando")
    ) {
  override def hashCode(): Int = 511006
}

final case class Gavriil()
    extends Character(
      NameProtocol()
        .ar("Gavriil")
        .de("Gavriil")
        .en("Gavriil")
        .es("Gavriil")
        .it("Gavriil")
        .fr("Gavriil")
        .ja("ガブリール")
        .pt("Gavriil")
        .ru("Гавриил")
        .uk("Гавриил")
        .vi("Gavriil")
        .zhCN("Gavriil")
        .zhTW("Gavriil")
    ) {
  override def hashCode(): Int = 511007
}

final case class Henrik()
    extends Character(
      NameProtocol()
        .ar("Henrik")
        .de("Henrik")
        .en("Henrik")
        .es("Henrik")
        .it("Henrik")
        .fr("Henrik")
        .ja("ヘンリック")
        .pt("Henrik")
        .ru("Хенрик")
        .uk("Хенрик")
        .vi("Henrik")
        .zhCN("Henrik")
        .zhTW("Henrik")
    ) {
  override def hashCode(): Int = 511008
}

final case class Ileanna()
    extends Character(
      NameProtocol()
        .ar("Ileanna")
        .de("Ileanna")
        .en("Ileanna")
        .es("Ileanna")
        .it("Ileanna")
        .fr("Ileanna")
        .ja("イレアナ")
        .pt("Ileanna")
        .ru("Илеанна")
        .uk("Илеанна")
        .vi("Ileanna")
        .zhCN("Ileanna")
        .zhTW("Ileanna")
    ) {
  override def hashCode(): Int = 511009
}

final case class Jasmin()
    extends Character(
      NameProtocol()
        .ar("Jasmin")
        .de("Jasmin")
        .en("Jasmin")
        .es("Jasmin")
        .it("Jasmin")
        .fr("Jasmin")
        .ja("ジャスミン")
        .pt("Jasmin")
        .ru("Ясмин")
        .uk("Ясмин")
        .vi("Jasmin")
        .zhCN("Jasmin")
        .zhTW("Jasmin")
    ) {
  override def hashCode(): Int = 511010
}

final case class Kaiji()
    extends Character(
      NameProtocol()
        .ar("Kaiji")
        .de("Kaiji")
        .en("Kaiji")
        .es("Kaiji")
        .it("Kaiji")
        .fr("Kaiji")
        .ja("開司")
        .pt("Kaiji")
        .ru("Каийи")
        .uk("Каийи")
        .vi("Kaiji")
        .zhCN("开司")
        .zhTW("開司")
    ) {
  override def hashCode(): Int = 511011
}

final case class Louise()
    extends Character(
      NameProtocol()
        .ar("Louise")
        .de("Louise")
        .en("Louise")
        .es("Louise")
        .it("Louise")
        .fr("Louise")
        .ja("ルイーズ")
        .pt("Louise")
        .ru("Лоуисе")
        .uk("Лоуисе")
        .vi("Louise")
        .zhCN("Louise")
        .zhTW("Louise")
    ) {
  override def hashCode(): Int = 511012
}

final case class Marthe()
    extends Character(
      NameProtocol()
        .ar("Marthe")
        .de("Marthe")
        .en("Marthe")
        .es("Marthe")
        .it("Marthe")
        .fr("Marthe")
        .ja("マーテ")
        .pt("Marthe")
        .ru("Мартхе")
        .uk("Мартхе")
        .vi("Marthe")
        .zhCN("Marthe")
        .zhTW("Marthe")
    ) {
  override def hashCode(): Int = 511013
}

final case class Nanyamka()
    extends Character(
      NameProtocol()
        .ar("Nanyamka")
        .de("Nanyamka")
        .en("Nanyamka")
        .es("Nanyamka")
        .it("Nanyamka")
        .fr("Nanyamka")
        .ja("ナニャンカ")
        .pt("Nanyamka")
        .ru("Наныамка")
        .uk("Наныамка")
        .vi("Nanyamka")
        .zhCN("Nanyamka")
        .zhTW("Nanyamka")
    ) {
  override def hashCode(): Int = 511014
}

final case class Oliwia()
    extends Character(
      NameProtocol()
        .ar("Oliwia")
        .de("Oliwia")
        .en("Oliwia")
        .es("Oliwia")
        .it("Oliwia")
        .fr("Oliwia")
        .ja("オリビア")
        .pt("Oliwia")
        .ru("Олиwиа")
        .uk("Олиwиа")
        .vi("Oliwia")
        .zhCN("Oliwia")
        .zhTW("Oliwia")
    ) {
  override def hashCode(): Int = 511015
}

final case class Ryan()
    extends Character(
      NameProtocol()
        .ar("Ryan")
        .de("Ryan")
        .en("Ryan")
        .es("Ryan")
        .it("Ryan")
        .fr("Ryan")
        .ja("ライアン")
        .pt("Ryan")
        .ru("Рыан")
        .uk("Рыан")
        .vi("Ryan")
        .zhCN("Ryan")
        .zhTW("Ryan")
    ) {
  override def hashCode(): Int = 511016
}

final case class Susan()
    extends Character(
      NameProtocol()
        .ar("Susan")
        .de("Susan")
        .en("Susan")
        .es("Susan")
        .it("Susan")
        .fr("Susan")
        .ja("スーザン")
        .pt("Susan")
        .ru("Сусан")
        .uk("Сусан")
        .vi("Susan")
        .zhCN("Susan")
        .zhTW("Susan")
    ) {
  override def hashCode(): Int = 511017
}

final case class Thuy()
    extends Character(
      NameProtocol()
        .ar("Thuy")
        .de("Thuy")
        .en("Thuy")
        .es("Thuy")
        .it("Thuy")
        .fr("Thuy")
        .ja("ティー")
        .pt("Thuy")
        .ru("Тхуы")
        .uk("Тхуы")
        .vi("Thúy")
        .zhCN("Thuy")
        .zhTW("Thuy")
    ) {
  override def hashCode(): Int = 511018
}

final case class Uma()
    extends Character(
      NameProtocol()
        .ar("Uma")
        .de("Uma")
        .en("Uma")
        .es("Uma")
        .it("Uma")
        .fr("Uma")
        .ja("ウマ")
        .pt("Uma")
        .ru("Ума")
        .uk("Ума")
        .vi("Uma")
        .zhCN("Uma")
        .zhTW("Uma")
    ) {
  override def hashCode(): Int = 511019
}

final case class Valeria()
    extends Character(
      NameProtocol()
        .ar("Valeria")
        .de("Valeria")
        .en("Valeria")
        .es("Valeria")
        .it("Valeria")
        .fr("Valeria")
        .ja("ヴァレリア")
        .pt("Valeria")
        .ru("Валериа")
        .uk("Валериа")
        .vi("Valeria")
        .zhCN("Valeria")
        .zhTW("Valeria")
    ) {
  override def hashCode(): Int = 511020
}

final case class Yihan()
    extends Character(
      NameProtocol()
        .ar("Yihan")
        .de("Yihan")
        .en("Yihan")
        .es("Yihan")
        .it("Yihan")
        .fr("Yihan")
        .ja("イーハン")
        .pt("Yihan")
        .ru("Ыихан")
        .uk("Ыихан")
        .vi("Yihan")
        .zhCN("艺涵")
        .zhTW("藝涵")
    ) {
  override def hashCode(): Int = 511021
}
