(function() {
  'use strict';

  angular
    .module('app.layout')
    .directive('htTopNav', htTopNav);

  /* @ngInject */
  function htTopNav() {
    var directive = {
      bindToController: true,
      controller: TopNavController,
      controllerAs: 'vm',
      restrict: 'EA',
      scope: {
        'navline': '='
      },
      templateUrl: 'app/layout/ht-top-nav.html'
    };

    TopNavController.$inject = ['$rootScope', '$location'];

    /* @ngInject */
    function TopNavController($rootScope, $location) {
      var vm = this;
      $rootScope.isCollapsed = true;

      vm.logout = function() {
        $rootScope.currentUser = null;
        window.localStorage.removeItem('token');
        window.localStorage.removeItem('admin');
        $location.path('/');
      };
    }

    return directive;
  }
})();
