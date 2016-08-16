(function() {
  'use strict';

  angular
    .module('app.task')
    .controller('TaskController', TaskController);

  TaskController.$inject = ['logger', 'taskService'];
  /* @ngInject */
  function TaskController(logger, taskService) {
    var vm = this;
    vm.title = 'Task';
    vm.tasks = [];
    vm.currentUser = window.localStorage.getItem('token');
    vm.isAdmin = window.localStorage.getItem('admin');
    vm.selectedTask = {};
    vm.selectTask = selectTask;

    activate();
    getTasks();

    function activate() {
      //logger.info('Activated Task View');
    }

    function selectTask(task) {
      vm.selectedTask = task;
    }

    function getTasks() {
      return taskService.getTasks().then(function(data) {
        vm.tasks = data;
        return vm.tasks;
      });
    }
    vm.saveTask = function saveTask() {
      vm.task.user = {id : vm.currentUser};
      var task = taskService.save(vm.task);
      if (task != null) {
        getTasks();
      }
    };

    vm.filterUserTasks = function() {
      return function(data) {

        if (data.user != null) {
          var id = data.user.id;
          if (id === vm.currentUser) {
            return true;
          }
        }
        return false;
      };
    };
  }
})();
