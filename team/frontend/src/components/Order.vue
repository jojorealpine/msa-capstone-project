<template>

    <v-card style="width:300px;" outlined>
        <template slot="progress">
            <v-progress-linear
                    color="deep-purple"
                    height="10"
                    indeterminate
            ></v-progress-linear>
        </template>

        <v-img
            style="width:290px; height:150px; border-radius:10px; position:relative; margin-left:5px; top:5px;"
            :style="editMode ? 'cursor:pointer;':''"
            :src="value.photo ? value.photo:'https://cdn.vuetifyjs.com/images/cards/cooking.png'"
            @click="selectFile()"
        ></v-img>

        <v-card-title v-if="value._links">
            Order # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
        </v-card-title >
        <v-card-title v-else>
            Order
        </v-card-title >

        <v-card-text>
            <div>
                <Number label="OrderId" v-model="value.orderId" :editMode="editMode"/>
            </div>
            <div>
                <Number label="CustomerId" v-model="value.customerId" :editMode="editMode"/>
            </div>
            <div>
                <String label="ProductName" v-model="value.productName" :editMode="editMode"/>
            </div>
            <div>
                <Number label="ProductPrice" v-model="value.productPrice" :editMode="editMode"/>
            </div>
            <div>
                <Number label="OrderTotalPrice" v-model="value.orderTotalPrice" :editMode="editMode"/>
            </div>
            <div>
                <String label="OrderStatus" v-model="value.orderStatus" :editMode="editMode"/>
            </div>
            <div>
                <Date label="OrderDate" v-model="value.orderDate" :editMode="editMode"/>
            </div>
            <div>
                <String label="CustomerAddr" v-model="value.customerAddr" :editMode="editMode"/>
            </div>
            <div>
                <String label="CustomerTel" v-model="value.customerTel" :editMode="editMode"/>
            </div>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="edit"
                    v-if="!editMode"
            >
                Edit
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="save"
                    v-else
            >
                Save
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="remove"
                    v-if="!editMode"
            >
                Delete
            </v-btn>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="editMode = false"
                    v-if="editMode && !isNew"
            >
                Cancel
            </v-btn>
        </v-card-actions>
        <v-card-actions>
            <v-spacer></v-spacer>                        
        </v-card-actions>
    </v-card>

</template>

<script>
    const axios = require('axios').default;

    import String from './primitives/String.vue';
    import Number from './primitives/Number.vue';
    import Boolean from './primitives/Boolean.vue';
    import Date from './primitives/Date.vue';

    
    export default {
        name: 'Order',
        components:{
            String,
            Number,
            Boolean,
            Date,
        },
        props: {
            value: [Object, String, Number, Boolean, Array],
            editMode: Boolean,
            isNew: Boolean,
            offline: Boolean
        },
        data: () => ({
        }),
        created(){
            if(!this.value) {
                this.value = {};
            }

            if(typeof this.value === 'object') {
                if(!('orderId' in this.value)) {
                    this.value.orderId = null;
                }
                if(!('customerId' in this.value)) {
                    this.value.customerId = null;
                }
                if(!('productName' in this.value)) {
                    this.value.productName = null;
                }
                if(!('productPrice' in this.value)) {
                    this.value.productPrice = null;
                }
                if(!('orderTotalPrice' in this.value)) {
                    this.value.orderTotalPrice = null;
                }
                if(!('orderStatus' in this.value)) {
                    this.value.orderStatus = null;
                }
                if(!('orderDate' in this.value)) {
                    this.value.orderDate = null;
                }
                if(!('customerAddr' in this.value)) {
                    this.value.customerAddr = null;
                }
                if(!('customerTel' in this.value)) {
                    this.value.customerTel = null;
                }
            }            
        },

        methods: {
            selectFile(){
            if(this.editMode == false) {
                return false;
            }
                var me = this
                var input = document.createElement("input");
                input.type = "file";
                input.accept = "image/*";
                input.id = "uploadInput";
                
                input.click();
                input.onchange = function (event) {
                    var file = event.target.files[0]
                    var reader = new FileReader();

                    reader.onload = function () {
                        var result = reader.result;
                        me.imageUrl = result;
                        me.value.photo = result;
                        
                    };
                    reader.readAsDataURL( file );
                };
            },
            edit() {
                this.editMode = true;
            },
            async save(){
                try {
                    var temp = null;

                    if(!this.offline) {
                        if(this.isNew) {
                            temp = await axios.post(axios.fixUrl('/orders'), this.value)
                        } else {
                            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
                        }
                    }

                    if(this.value!=null) {
                        for(var k in temp.data) this.value[k]=temp.data[k];
                    } else {
                        this.value = temp.data;
                    }

                    this.editMode = false;
                    this.$emit('input', this.value);

                    if (this.isNew) {
                        this.$emit('add', this.value);
                    } else {
                        this.$emit('edit', this.value);
                    }

                } catch(e) {
                    alert(e.message)
                }
                location.reload()
            },
            async remove(){
                try {
                    if (!this.offline) {
                        await axios.delete(axios.fixUrl(this.value._links.self.href))
                    }

                    this.editMode = false;
                    this.isDeleted = true;

                    this.$emit('input', this.value);
                    this.$emit('delete', this.value);

                } catch(e) {
                    alert(e.message)
                }
            },
            change(){
                this.$emit('input', this.value);
            },
        },
    }
</script>

