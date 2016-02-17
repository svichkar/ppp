function setMenu(menuItem) {
    $('li').removeClass('active');
    $(menuItem).addClass('active');
}

var StudentsView = Backbone.View.extend({
                 el: $('.content'),
                 template: _.template($('#students').html()),
                 initialize: function () {
                     this.render();
                 },
                 render: function () {
                     setMenu('.students');
                     students.fetch({async: false});
                     var models = {"students": students.toJSON()};
                     this.$el.html(this.template(models));
                 }
});

 var AddStudentView = Backbone.View.extend({
                 el: $('.content'),
                 events: {
                     'click #addSbmt': 'submitForm'
                 },
                 template: _.template($('#addStudent').html()),
                 initialize: function () {
                     this.render();
                 },
                 render: function () {
                     setMenu('.addStudent');
                     this.$el.html(this.template({}));
                 },
                 submitForm: function() {
                     var student = new StudentModel();
                     student.set('firstName', $('#firstName').val());
                     student.set('lastName', $('#lastName').val());
                     student.save({async: false});
                     window.location.hash = 'students';
                 }
});

var EditStudentView = Backbone.View.extend({
                 el: $('.content'),
                 events: {
                     'click #updateSbmt': 'submitForm'
                 },
                 template: _.template($('#editStudent').html()),
                 initialize: function (options) {
                     this.product = new StudentModel();
                     this.product.set('id', options.id);
                     this.product.fetch({async: false});
                     this.render();
                 },
                 render: function () {
                     var model = {"student": this.student.toJSON()};
                     this.$el.html(this.template(model));
                 },
                 submitForm: function() {
                     var student = new StudentModel();
                     student.set('firstName', $('#firstName').val());
                     student.set('lastName', $('#lastName').val());
                     student.save({async: false});
                     window.location.hash = 'students';
                 }
});