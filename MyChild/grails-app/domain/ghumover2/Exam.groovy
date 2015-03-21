package ghumover2

class Exam {

    String examId
    String examName
    String examType
	Grade grade

    static constraints = {
		
		examType(nullable:true)
    }
}
