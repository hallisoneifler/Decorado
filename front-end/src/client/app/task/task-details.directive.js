(function() {
  'use strict';

  angular
    .module('app.task')
    .directive('taskDetail', taskDetail);

  /* @ngInject */
  function taskDetail() {
    var directive = {
      restrict: 'E',
      scope: {
        'detail': '=task'
      },
      templateUrl: 'app/task/task-detail.html'
    };
    return directive;
  }
})();
