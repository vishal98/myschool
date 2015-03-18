package ghumover2

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured;

import java.text.SimpleDateFormat

@Secured(['ROLE_PARENT'])
class ParentController {
	def accountInfo(){
		def article=new Father()
		def articleList=article.list()
		int id= Integer.parseInt(params.id)
		def trek=article.findAllWhere(parentId:id)
		println articleList
		
		
		JSON.use('father') {
			render trek as JSON
		}
	
	}
	def getHomeWork(){
		def article=new Homework()
		def articleList=article.list()
		int id= Integer.parseInt(params.id)
		def trek=article.findAllWhere(studentId:id)
		trek.sort{it.dueDate}
		println articleList
		
		render trek as JSON
		/*JSON.use('father') {
			render trek as JSON
		}*/
	}
	def getTodayHomeWork(){
		def article=new Homework()
		def articleList=article.list()
		int tid= Integer.parseInt(params.id)
		
		Date date = new Date()
		def from = date.clearTime()
		def to = from + 1
		def query = Homework.where{
			studentId == tid
			dateCreated in (from .. to)
			
		}
		def results = query.list()
		render results as JSON
		/*JSON.use('father') {
			render trek as JSON
		}*/
	}
}
