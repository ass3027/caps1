<template>
  <div>
    <h2>플래너 공유 게시판</h2>
    <template>
      <v-simple-table dark dense
      >
          <thead>
          <tr>
            <th class="text-center">
              pl_name
            </th>
            <th class="text-center">
              supl_name
            </th>
          </tr>
          </thead>
          <tbody>
          <tr
              v-for="(item,index) in tableSets"
              :key="index"
          >
            <td>{{ item.pl_name }}</td>
            <td>{{ item.supl_name }}</td>
          </tr>
          </tbody>
      </v-simple-table>
    </template>
    {{tableSets}}
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "ImportSuppliesView",
  components:{
  },
  data(){
    return{
      sets:[],
      tableSets:[],

    }
  },
  mounted() {
    axios.get('/api/getSets')
    .then((res)=>{
      this.tableSets=res.data;
      console.log(res.data);
      var outerArray = [[{pl_id:0,pl_name:"기본",supl_id:"1",supl_name:"수건"},{pl_id:0,pl_name:"기본",supl_id:"3",supl_name:"여권"}]];
      var innerArray = [];
      var id = res.data[0].pl_id;
      res.data.forEach((i)=>{

        if(i.pl_id!=id) {

          outerArray.push(innerArray);
          innerArray = [];
          id = i.pl_id;
        }
        innerArray.push(i);

      })
      outerArray.push(innerArray);
      this.sets=outerArray;
    })
  }
}
</script>

<style scoped>

</style>