(function() {
  'use strict';

  var core = angular.module('app.core');

  core.config(restangularConfig);

  restangularConfig.$inject = ['RestangularProvider'];
  /* @ngInject */
  function restangularConfig(RestangularProvider) {
    RestangularProvider.setBaseUrl('http://localhost:8080/app_decorado/api');
  }

  core.config(toastrConfig);

  toastrConfig.$inject = ['toastr'];
  /* @ngInject */
  function toastrConfig(toastr) {
    toastr.options.timeOut = 4000;
    toastr.options.positionClass = 'toast-bottom-right';
  }

  var config = {
    appErrorPrefix: '[decorado Error] ',
    appTitle: 'Decorado'
  };

  core.value('config', config);

  core.config(configure);

  configure.$inject = ['$logProvider', 'routerHelperProvider', 'exceptionHandlerProvider'];
  /* @ngInject */
  function configure($logProvider, routerHelperProvider, exceptionHandlerProvider) {
    if ($logProvider.debugEnabled) {
      $logProvider.debugEnabled(true);
    }
    exceptionHandlerProvider.configure(config.appErrorPrefix);
    routerHelperProvider.configure({ docTitle: config.appTitle + ': ' });
  }

})();
