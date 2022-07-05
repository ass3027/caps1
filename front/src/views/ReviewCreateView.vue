<template>
  <div>
    <span>{{ id }}</span>
    <span>{{ type }}</span>

    <review-form
      :id="id"
      :type="type"
    />
  </div>
</template>

<script>
import ReviewForm from "@/components/review/ReviewForm";
import axios from "axios";

export default {
  name: 'ReviewCreateView',
  components: {
    ReviewForm,
  },
  props: {
    type: String,
    id: String,
  },
  data() {
    return {
      book_id: '',

    }
  },
  mounted() {
    var sendForm = new FormData()

    console.log("pay_id" + this.pay_id)
    const pay_id = this.pay_id + ""
    axios({
      method: 'GET',
      url: 'http://localhost:8080/api/findByPay?pay_id=' + pay_id,

    })
      .then((res) => {
        console.log(res.data)
        console.log(1)
        this.book_id = "" + res.data;
      })
      .catch((err) => {
        console.log(err)
      })
  },


}
</script>

