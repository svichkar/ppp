window.StudentModel = Backbone.Model.extend({
    urlRoot:"ws/rest/students/getStudent",
});

var StudentsCollection = Backbone.Collection.extend({
     model: StudentModel,
     url: "getAllStudents"
    });


var student = new StudentModel();
student.set({ studentId: 5 });

student.fetch({
           success: function (student) {
               alert(JSON.stringify(student));
           }
       });