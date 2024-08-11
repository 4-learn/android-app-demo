    // btn_home - 切換到計算BMI的佈局
    private View.OnClickListener goToBMIcal = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setContentView(R.layout.cal);

            // 在新佈局中再次綁定視圖並設置事件處理器
            Button calculateButton = findViewById(R.id.button);
            calculateButton.setOnClickListener(calcBMI);
        }
    };
