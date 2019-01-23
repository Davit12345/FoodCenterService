
var messageApi = Vue.resource('/users');

Vue.component('message-form', {
    props: ['messages', 'messageAttr'],
    data: function() {
        return {
            email: '',
            password: '',
            name: '',
            surname: '',
            phoneNumber: '',
            age: '',
            id: ''
        }
    },

    template:
        '<div>' +
        '<input type="text" placeholder="Write Email" v-model="email" />' +
        '<input type="text" placeholder="Write Password" v-model="password" />' +
        '<input type="text" placeholder="Write Name" v-model="name" />' +
        '<input type="text" placeholder="Write Surname" v-model="surname" />' +
        '<input type="text" placeholder="Write Phone Number " v-model="phoneNumber" />' +
        '<input type="text" placeholder="Write age" v-model="age" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var message = { email: this.email,
                password: this.password,
                name: this.name,
                surname: this.surname,
                phoneNumber: this.phoneNumber,
                age: this.age
            };


                messageApi.save({}, message).then(result =>
                result.json().then(data => {
                    this.messages.push(data);
                this.email = '',
                    this.password = '',
                    this.name = '',
                    this.surname = '',
                    this.phoneNumber = '',
                    this.age = ''



            })
            )
            }
        }

});



Vue.component('messages-list', {
    props: ['messages'],
    data: function() {
        return {
            message: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<message-form :messages="messages" :messageAttr="message" />' +

        '</div>',

    created: function() {
        messageApi.get().then(result =>
        result.json().then(data =>
        data.forEach(message => this.messages.push(message))
    )
    )

    }
});

var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
