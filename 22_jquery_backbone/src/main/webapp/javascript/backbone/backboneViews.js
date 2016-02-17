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
                     this.statusList = new StatusCollection();
                     this.termList = new TermCollection();
                     this.groupList = new GroupCollection();
                     this.statusList.fetch({async: false});
                     this.termList.fetch({async: false});
                     this.groupList.fetch({async: false});
                     this.render();
                 },
                 render: function () {
                     setMenu('.addStudent');
                     var model = {"statusList": this.statusList.toJSON(),
                     "termList": this.termList.toJSON(),
                     "groupList": this.groupList.toJSON(),
                     };
                     this.$el.html(this.template(model));
                 },
                 submitForm: function() {
                     var student = new StudentModel();
                     student.set('firstName', $('#firstName').val());
                     student.set('lastName', $('#lastName').val());
                     student.set('admissionDate', $('#admissionDate').val());
                     student.set('studentGroup', {groupId: Number($('#studentGroup').val()),
                                                  groupName: $( "#studentGroup option:selected" ).text()
                                                 });
                     student.set('term', {termId: Number($('#term').val()),
                                          termName: $( "#term option:selected" ).text()
                                          });
                     student.set('status', {statusId: Number($('#status').val()),
                                            statusName: $( "#status option:selected" ).text()
                                            });
                     student.save();
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
                     this.student = new StudentModel();
                     this.statusList = new StatusCollection();
                     this.termList = new TermCollection();
                     this.groupList = new GroupCollection();
                     this.student.set('id', options.id);
                     this.student.fetch({async: false});
                     this.statusList.fetch({async: false});
                     this.termList.fetch({async: false});
                     this.groupList.fetch({async: false});
                     this.render();
                 },
                 render: function () {
                     var model = {"student": this.student.toJSON(),
                     "statusList": this.statusList.toJSON(),
                     "termList": this.termList.toJSON(),
                     "groupList": this.groupList.toJSON(),
                     };
                     this.$el.html(this.template(model));
                 },
                 submitForm: function() {
                     var student = new StudentModel();
                     student.set('id', $('#id').val());
                     student.set('firstName', $('#firstName').val());
                     student.set('lastName', $('#lastName').val());
                     student.set('admissionDate', $('#admissionDate').val());
                     student.set('studentGroup', {groupId: Number($('#studentGroup').val()),
                                                  groupName: $( "#studentGroup option:selected" ).text()
                                                 });
                     student.set('term', {termId: Number($('#term').val()),
                                          termName: $( "#term option:selected" ).text()
                                          });
                     student.set('status', {statusId: Number($('#status').val()),
                                            statusName: $( "#status option:selected" ).text()
                                            });
                     student.save();
                     window.location.hash = 'students';
                 }
});