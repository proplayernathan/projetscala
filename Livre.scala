import scala.collection.mutable.ArrayBuffer

class Livre(var titre: String, var auteur: String, var anneeDePublication: Int) {
  var estEmprunte: Boolean = false

  def emprunter(): Unit = {
    estEmprunte = true
    println("Le livre a été emprunté.")
  }

  def rendre(): Unit = {
    estEmprunte = false
    println("Le livre a été rendu.")
  }
}

class Bibliotheque {
  val listeDeLivres: ArrayBuffer[Livre] = new ArrayBuffer[Livre]()

  def ajouterLivre(titre: String, auteur: String, annee: Int): Unit = {
    val nouveauLivre = new Livre(titre, auteur, annee)
    listeDeLivres += nouveauLivre
  }

  def emprunterLivre(titre: String): Unit = {
    val livreOption = listeDeLivres.find(_.titre == titre)
    livreOption match {
      case Some(livre) =>
        if (!livre.estEmprunte) {
          livre.emprunter()
        } else {
          println("Le livre n'est pas disponible pour le moment.")
        }
      case None =>
        println("Le livre n'est pas disponible dans la bibliothèque.")
    }
  }

  def rendreLivre(titre: String): Unit = {
    val livreOption = listeDeLivres.find(_.titre == titre)
    livreOption match {
      case Some(livre) =>
        if (livre.estEmprunte) {
          livre.rendre()
        } else {
          println("Le livre n'est pas emprunté pour le moment.")
        }
      case None =>
        println("Le livre n'existe pas dans cette bibliothèque.")
    }
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val bibliotheque = new Bibliotheque()

    var running = true
    while (running) {
      println("\nQue voulez-vous faire ?")
      println("1. Ajouter un livre")
      println("2. Emprunter un livre")
      println("3. Rendre un livre")
      println("4. Quitter")

      val choix = scala.io.StdIn.readLine("Entrez le numéro de votre choix : ")

      choix match {
        case "1" =>
          val titre = scala.io.StdIn.readLine("Entrez le titre du livre : ")
          val auteur = scala.io.StdIn.readLine("Entrez le nom de l'auteur : ")
          val annee = scala.io.StdIn.readLine("Entrez l'année de publication : ").toInt
          bibliotheque.ajouterLivre(titre, auteur, annee)

        case "2" =>
          val titre = scala.io.StdIn.readLine("Entrez le titre du livre que vous souhaitez emprunter : ")
          bibliotheque.emprunterLivre(titre)

        case "3" =>
          val titre = scala.io.StdIn.readLine("Entrez le titre du livre que vous souhaitez rendre : ")
          bibliotheque.rendreLivre(titre)

        case "4" =>
          println("Merci d'avoir utilisé la bibliothèque.")
          running = false

        case _ =>
          println("Choix invalide. Veuillez entrer un numéro correspondant à l'action souhaitée.")
      }
    }
  }
}
