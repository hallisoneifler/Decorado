(function() {
  'use strict';

  angular
  .module('app.core')
  .controller('LoginController', LoginController);

  LoginController.$inject = ['logger', 'loginService', '$location'];

  /* @ngInject */
  function LoginController(logger, loginService, $location) {
    var vm = this;

    vm.login = function login() {
      return loginService.login(vm.email, vm.password).then(function(data) {
        if (data) {
          window.localStorage.setItem('token', data.id);
          window.localStorage.setItem('admin', data.admin);
          $location.path('/user');
        } else {
          vm.email = '';
          vm.password = '';
        }
      });
    };
  }
})();
