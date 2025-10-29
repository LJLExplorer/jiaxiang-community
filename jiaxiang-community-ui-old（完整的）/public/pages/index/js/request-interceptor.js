navigator.serviceWorker.register("../../debug-service.js", { type: "module" })
    .then(serviceWorkerRegistration => {
    console.log("网络拦截器已注册");
});
