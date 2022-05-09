<template>
  <div>
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
