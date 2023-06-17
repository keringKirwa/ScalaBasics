class LoanApplication {

}

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

object LoanLendingSystem extends App {
  private def getCreditScore(borrowerId: String): Future[Int] = Future {
    Thread.sleep(2000)

    val randomScore = scala.util.Random.nextInt(800) + 200
    randomScore
  }

  private def processLoanApplication(borrowerId: String): Future[String] = {
    val creditScoreFuture: Future[Int] = getCreditScore(borrowerId)

    val loanProcessingFuture: Future[String] = creditScoreFuture.map { creditScore =>
      if (creditScore > 500) {
        "Loan approved"
      } else {
        "Loan rejected"
      }
    }

    loanProcessingFuture
  }

  private val loanApplicationFuture: Future[String] = processLoanApplication("12345")

  loanApplicationFuture.foreach { result =>
    println("Loan application result: " + result)
  }

  Thread.sleep(3000)
}

