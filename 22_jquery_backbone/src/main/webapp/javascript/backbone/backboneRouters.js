var Workspace = Backbone.Router.extend({

routes: {
            "": "students",
            "students": "students",
            "addStudent": "addStudent",
            "editStudent/:id": "editStudent",
            "deleteStudent/:id": "deleteStudent"
},

initialize: function () {
             Backbone.history.start();
         },

         students: function () {
             new StudentsView();
         },

         addStudent: function () {
             new AddStudentView();
         },

         editStudent: function (id) {
             new EditStudentView({id: id});
         },

         deleteStudent: function(id) {
             var student = new StudentModel;
             student.set('id', id);
             student.destroy();
             window.location.hash = 'students';
         }
});

var mainRouter = new Workspace();