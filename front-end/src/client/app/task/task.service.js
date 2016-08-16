(function() {
  'use strict';

  angular
    .module('app.core')
    .service('taskService', taskService);

  taskService.$inject = ['Restangular', '$rootScope', 'exception', 'logger'];
  /* @ngInject */
  function taskService(Restangular, $rootScope, exception, logger) {
    var Task = Restangular.all('tasks');

    var service = {
      getTasks:getTasks,
      save:saveTask
    };
    return service;

    function saveTask(task) {
      return Task.post(task)
      .then(success)
      .catch(fail);
      function success(response) {
        return response.plain();
      }
      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }

    function getTasks(user) {
      return Task.getList()
        .then(success)
        .catch(fail);

      function success(response) {
        return response.plain();
      }

      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }
  }
})();
