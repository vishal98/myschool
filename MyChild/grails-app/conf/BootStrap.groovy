
import ghumover2.*;

						

						class BootStrap {

						    private static final String ROLE_TEACHER = 'ROLE_TEACHER'
                                                    private static final String ROLE_PARENT = 'ROLE_PARENT'
						  

						    def init = { servletContext ->

						        Role roleTeacher;
                                                        Role roleParent;
						        Teacher teacher;
                                                        Parent parent;

						       
						            roleTeacher = new Role(authority: ROLE_TEACHER)
						            roleTeacher.save()
                                                            
                                                            roleParent = new Role(authority: ROLE_PARENT)
						            roleParent.save()
						       


						      
						            teacher = new Teacher(username: 'test_teacher', password: "123" , teacherId:"100" , teacherName:"John" , teacherPhoto:"100.jpg")
                                                            teacher.save()
                                                             new UserRole(user:teacher , role:roleTeacher).save() 
                                                        
                                                                
                                                           
                                                            
                                                        
                                                        
                                                        
                                                        
                                                            parent = new Parent(username: 'test_parent', password: "123" ,parentName:"Ravi")
                                                            parent.save()
                                                            new UserRole(user:parent , role:roleParent).save() 
						       

						       
						            
						        
						    }

						    def destroy = {
						    }
						}