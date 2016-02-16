window.StudentModel = Backbone.Model.extend({
    urlRoot:"/backbone/ws/rest/students/getStudent",
});

window.StudentsCollection = Backbone.Collection.extend({
     model: StudentModel,
     url: "/backbone/ws/rest/students/getAllStudents"
    });


var student = new StudentModel();
student.set ({id: 5});

var studentList = new StudentsCollection();

studentList.fetch({
                             success: function (studentList) {
                                 alert(JSON.stringify(studentList));
                             }
                         })

student.fetch({
           success: function (student) {
               alert(JSON.stringify(student));
           }
       });

var updated = new StudentModel(
{
id: 5,
firstName: "Marlon",
lastName: "Bullocks",
studentGroup: {
groupId: 3,
groupName: "java 15-3"
},
admissionDate: "2015-01-01",
status: {
statusId: 1,
statusName: "active"
},
term: {
termId: 1,
termName: "first"
}
});


updated.urlRoot = "/backbone/ws/rest/students/updateStudent";
updated.save({
             success: function (updated) {
             alert(JSON.stringify(updated));
             }
});
