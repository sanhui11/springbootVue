<template>
  <div style="margin: 0 auto" class="container w">
    <el-row :gutter="20">
      <el-col>
        <div class="card-wrapper">
          <div class="card-column">
            <div :span="4" v-for="item in tableData.slice(0,3)" class="card" :body-style="{padding:'0px'}" shadow="hover">
              <div class="card-info">
                <div class="card-img">
                  <img :src="item.avatarUrl" alt="" style="vertical-align: middle;text-align: center">
                </div>
                <div class="description">{{ item.title }}</div>
              </div>
              <div class="card-bottom clearfix">
                <div class="user-image-wrapper">
                  <img :src="item.avatarUrl" alt="">
                </div>
                <span class="name">{{ item.authorName }}</span>
                <div class="card-like">
                  <i class="el-icon-star-on"></i>
                  <span class="likes">{{ item.follow }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="card-column">
            <div :span="4" v-for="item in tableData.slice(3,6)" class="card" :body-style="{padding:'0px'}" shadow="hover">
              <div class="card-info">
                <div class="card-img">
                  <img :src="item.avatarUrl" alt="" style="vertical-align: middle;text-align: center">
                </div>
                <div class="description">{{ item.title }}</div>
              </div>
              <div class="card-bottom clearfix">
                <div class="user-image-wrapper">
                  <img :src="item.avatarUrl" alt="">
                </div>
                <span class="name">{{ item.authorName }}</span>
                <div class="card-like">
                  <i class="el-icon-star-on"></i>
                  <span class="likes">{{ item.follow }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="card-column">
            <div :span="4" v-for="item in tableData.slice(6,9)" class="card" :body-style="{padding:'0px'}" shadow="hover">
              <div class="card-info">
                <div class="card-img">
                  <img :src="item.avatarUrl" alt="" style="vertical-align: middle;text-align: center">
                </div>
                <div class="description">{{ item.title }}</div>
              </div>
              <div class="card-bottom clearfix">
                <div class="user-image-wrapper">
                  <img :src="item.avatarUrl" alt="">
                </div>
                <span class="name">{{ item.authorName }}</span>
                <div class="card-like">
                  <i class="el-icon-star-on"></i>
                  <span class="likes">{{ item.follow }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="card-column">
            <div :span="4" v-for="item in tableData.slice(9,12)" class="card" :body-style="{padding:'0px'}" shadow="hover">
              <div class="card-info">
                <div class="card-img">
                  <img :src="item.avatarUrl" alt="" style="vertical-align: middle;text-align: center">
                </div>
                <div class="description">{{ item.title }}</div>
              </div>
              <div class="card-bottom clearfix">
                <div class="user-image-wrapper">
                  <img :src="item.avatarUrl" alt="">
                </div>
                <span class="name">{{ item.authorName }}</span>
                <div class="card-like">
                  <i class="el-icon-star-on"></i>
                  <span class="likes">{{ item.follow }}</span>
                </div>
              </div>
            </div>
          </div>
          <div class="card-column">
            <div :span="4" v-for="item in tableData.slice(12,15)" class="card" :body-style="{padding:'0px'}" shadow="hover">
              <div class="card-info">
                <div class="card-img">
                  <img :src="item.avatarUrl" alt="" style="vertical-align: middle;text-align: center">
                </div>
                <div class="description">{{ item.title }}</div>
              </div>
              <div class="card-bottom clearfix">
                <div class="user-image-wrapper">
                  <img :src="item.avatarUrl" alt="">
                </div>
                <span class="name">{{ item.authorName }}</span>
                <div class="card-like">
                  <i class="el-icon-star-on"></i>
                  <span class="likes">{{ item.follow }}</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          :page-sizes="[12,15]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
export default {
  name: "Card",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 15,
      currentDate: new Date()
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.request.get("/vlog", {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize
        }
      },).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.records
          this.total = res.data.total
        }
      })
    },
    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(PageNum){
      this.pageNum = PageNum
      this.load()
    }
  }
}
</script>


<style>
a {
  text-decoration: none;
}

.container {
  width: 1226px;
  margin: 0 auto;
}

.card-wrapper {
  margin-top: 30px;
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: flex-start;
}

.card-column {
  margin: 0 20px 0 0;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
}

.card {
  width: 220px;
  margin: 0 0 20px;
  background-color: #fff;
  box-shadow: 0 1px 2px 0 rgb(0 0 0 / 10%);
  border-radius: 8px;
  overflow: hidden;
  cursor: pointer;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: center;
  position: relative;
}

.card-info {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: flex-start;
  align-items: flex-start;
}

.card-img {
  width: 100%;
  height: 293px;
  margin: 0 auto;
  position: relative;
  display: flex;
  overflow: hidden;
  border: 1px solid gainsboro;
}


.description {
  padding: 0;
  margin-top: 10px;
  margin-bottom: 10px;
  margin-left: 15px;
  color: #000;
  font-size: 13px;
  font-weight: 400;
  line-height: 24px;
}

.card-bottom {
  padding: 0 15px 10px;
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}

.user-image-wrapper {
  width: 28px;
  height: 28px;
  border-radius: 50%;
  position: relative;
  overflow: hidden
}

.card-bottom .name {
  width: 87px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  margin-left: 10px;
  color: #555;
  font-size: 12px;
}

.card-like .likes {
  width: 87px;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
  margin-left: 10px;
  color: #555;
  font-size: 12px;
}


</style>

