const alert = {
    alertData: {
        isDisplay: false,
        color: 'error',
        message: ''
    },
    show(color, message) {
        Object.assign(this.alertData, { isDisplay: true, color, message });
    },
    close() {
        Object.assign(this.alertData, { isDisplay: false, message: '' });
    },
    error(aMessage) {
        alert.show('error', aMessage);
    },
    warning(aMessage) {
        alert.show('warning', aMessage);
    },
    success(aMessage) {
        alert.show('success', aMessage);
    }
};

export default {
    install(Vue, pluginName = '$alert') {
        Vue.prototype[pluginName] = alert;
    }
};
export { alert };
