/**
 * Created by jmarzin-cp on 28/05/2016.
 */

import java.io.PrintWriter
import scala.io.StdIn.readLine
import scala.reflect.io.File



object cdif {
  def main(args: Array[String]): Unit = {

    val runtime = Runtime.getRuntime();

    val nomFichier = "C:\\cdif\\codes_cdif.txt"
    val fichierCodes = File(nomFichier)

    if (!fichierCodes.exists) {
      println("Fichier des codes absents")
      System.exit(1)
    }

    var codes = io.Source.fromFile(nomFichier).getLines.toSeq
    val centPremiersCodes = codes.slice(0, 99)

    var codeNonFourni = true
    var input = ""
    while (codeNonFourni) {
      input = readLine("Veuillez saisir le code qu'on vous a remis : ")
      if (centPremiersCodes.contains(input)) {
        codeNonFourni = false
        codes = codes.diff(Seq(input))
        fichierCodes.delete
        val fichierNouveauxCodes = new PrintWriter(nomFichier)
        codes.foreach(c => fichierNouveauxCodes.println(c))
        fichierNouveauxCodes.close
      }
    }

    val jeton = new PrintWriter("C:\\cdif\\jeton")
    jeton.close

    runtime.exec("C:\\Program Files\\Mozilla Firefox\\firefox.exe")
  }
}