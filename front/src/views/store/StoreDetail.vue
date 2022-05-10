<template>
  <div>
    <div>
      <div v-for="(product, i) in products" :key="i">
        <div>
          <p>{{ product.pd_name }}</p>
        </div>
        <div>
          <v-img width="50%"
                 height="50%"
                 :src="'/api/photo/' + product.pic_name"></v-img>
        </div>
      </div>
    </div>
    <br/>
    <StoreReviewView :storeId="store_id"/>
  </div>
</template>

<script>
import axios from "axios";
import StoreReviewView from "@/views/StoreReviewView";

export default {
  name: "StoreDetail",
  components: { StoreReviewView },
  props: ['store_id'],

  data: () => ({
    loading: false,
    selection: 1,
    products: [],
    mainpic: [],
  }),

  mounted() {
    axios({
      method: 'GET',
      url:'/api/product/',
      params: {'store_id': this.store_id}
    })
      .then(res => {
        this.products = res.data
        console.log(res.data)
        console.log(res.data[1].pic_name)
      })
      .catch((err) => {
        console.log(err)
      })
  },

  methods: {
    reserve () {
      this.loading = true
      setTimeout(() => (this.loading = false), 2000)
    },

    // importStore(){
    //   axios({
    //     method:'get',
    //     url: `/api/store/` + this.store_name,
    //   })
    //   .then((res)=>{
    //     this.lists = res.data;
    //   })
    // }
  },
}
</script>
