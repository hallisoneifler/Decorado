(function () {
  'use strict';

  angular
        .module('app.core')
        .service('loginService', loginService);

  loginService.$inject = ['Restangular', 'exception', 'logger'];

  /* @ngInject */
  function loginService(Restangular, exception, logger) {
    var service = {
      login : loginUser
    };
    return service;

    function loginUser(email, token) {
      var login = Restangular.one('users/login').customPOST(
        {email:email, password:token});
      return login;

      function success(response) {
        return response.data;
      }
      function fail(e) {
        return exception.catcher('Failed for ...')(e);
      }
    }

    function Logout() {
      window.localStorage.removeItem('token');
    }
  }
})();
