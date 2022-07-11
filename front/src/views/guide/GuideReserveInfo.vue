<template>
  <v-container>
    <v-app>

  <div>
    <v-select
    label="구매 상황"
    v-model="check"
    :items="buyStatus"
    style="width:150px"
    item-value="text"
    >

    </v-select>
 <div>
 <template v-if="check=='예약완료'">
  <v-data-table
    :headers="headers"
    :items="lists"
    :items-per-page="5"
    class="elevation-1"
    @click:row="views"
  >
  </v-data-table>
   </template>
 </div>
  </div>

      <div>
        <template v-if="check == '구매대기'">
          <v-data-table
            :headers="headers2"
            :items="lists2"
           :items-per-page="5"
           class="elevation-2"
            @click:row="views"
          >
            <template v-slot:[`item.actions`]="{ item }">
              <v-btn
                small
                id="btn"
                class="mr-2"
                @click="buyItem(item)"
              >
                구매확정
              </v-btn>
              <v-icon

                small
                @click="deleteItem(item)"
              >
                mdi-delete
              </v-icon>
            </template>
          </v-data-table>
        </template>
      </div>

    </v-app>
  </v-container>
</template>


<script>
import axios from "axios";

export default {
  name: "GuideReserveInfo.vue",
  data(){
    return{
      lists2:[],
      lists:[],
      selecteditem:'',
      headers:[
        {text:'예약한 날짜' ,value:'gdate'},
        {text:'시작시간', value:'st_time'},
        {text:'종료시간', value:'end_time'},
        {text:'소요시간', value:'require_time'},
        {text:'장소명', value:'title'},
        {text:'가격', value:'pay_price'},
        {text:'ACTION',value:'actions', sortable:false}
      ],
      headers2:[
        {text:'예약한 날짜' ,value:'gdate'},
        {text:'시작시간', value:'st_time'},
        {text:'종료시간', value:'end_time'},
        {text:'소요시간', value:'require_time'},
        {text:'장소명', value:'title'},
        {text:'가격', value:'pay_price'},
        {text:'ACTION',value:'actions', sortable:false}
      ],
      buyStatus:[
        {text:'구매대기',value:'waitBuy'},
        {text:'예약완료', value: 'reserve'}
      ],
      check:'구매대기'
    }
  },
  mounted() {
    this.importGuide()
  },
  methods:{
    importGuide(){
      axios({
        method:'get',
        url:'/api/gTime',
        params:{
          'id':this.$store.state.user.userId
        }
      })
      .then((res)=>{
        this.lists = res.data;
      })
      axios({
        method:'get',
        url:'/api/gTime2',
        params:{
          'id':this.$store.state.user.userId
        }
      })
        .then((res)=>{
          this.lists2 = res.data;
        })

    },
    views(items){

      this.$router.push(  "/GuideProduct/" + items.gitem_id)
    },
    refreshAll(){
      this.$router.go()
    },
    buyItem(items){
      this.items = items
      console.log(this.items)
      axios({
        method:'put',
        url:'/api/gTimeUpdate',
        params:{
          'id': this.items.pay_id
        }
      })
        .then(()=>{
          this.$router.go()
        })
    },
    deleteItem(items) {
      this.items = items
      axios({
        method:'delete',
        url:'/api/dPay',
        params:{
          'id': this.items.pay_id
        }
      })
        .then(()=>{
          this.$router.go()
        })
    },

  }
}
</script>

<style scoped>

</style>
