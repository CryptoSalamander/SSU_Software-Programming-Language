
// TicTacToeDlg.h : 헤더 파일
//

#pragma once

#include "TicTacToeAI.h"
#include <iostream>
#include <queue>

using namespace std;
// CTicTacToeDlg 대화 상자

class CTicTacToeDlg : public CDialogEx
{
// 생성입니다.
public:
	CTicTacToeDlg(CWnd* pParent = NULL);	// 표준 생성자입니다.

// 대화 상자 데이터입니다.
	enum { IDD = IDD_TICTACTOE_DIALOG };

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);	// DDX/DDV 지원입니다.


// 구현입니다.
protected:
	HICON m_hIcon;

	// 생성된 메시지 맵 함수
	virtual BOOL OnInitDialog();
	afx_msg void OnSysCommand(UINT nID, LPARAM lParam);
	afx_msg void OnPaint();
	afx_msg HCURSOR OnQueryDragIcon();
	DECLARE_MESSAGE_MAP()

public:
	afx_msg void OnBnClickedButtonStart();
	afx_msg void OnBnClickedButtonInit();
	afx_msg void OnBnClickedButtonLoad();
	afx_msg void OnBnClickedButtonExit();
	afx_msg void OnBnClickedButtonUndoA();
	afx_msg void OnBnClickedButtonUndoB();
	virtual BOOL PreTranslateMessage(MSG* pMsg);	
	afx_msg HBRUSH OnCtlColor(CDC* pDC, CWnd* pWnd, UINT nCtlColor);
	HACCEL m_hAccelTable;
	CButton m_undoA;
	CButton m_undoB;
	CButton B1;
	CButton B2;
	CButton B3;
	CButton B4;
	CButton B5;
	CButton B6;
	CButton B7;
	CButton B8;
	CButton B9;
	CButton B10;
	CButton B11;
	CButton B12;
	CButton B13;
	CButton B14;
	CButton B15;
	CButton B16;
	CComboBox m_comboA;
	CEdit m_listA;
	HBRUSH m_BKColor;

	/* 사용자 지정 함수 */

	void SetGame();			/* 게임 초기 화면 셋팅 */
	int CheckReady();		/* 게임 시작 준비 상태를 검사 */
	int WaitUndo();			/* 무르기를 기다림 */
	void UpdateGame();		/* 게임 수를 화면에 업데이트 */
	void ResetGame();		/* 게임판을 다시 초기화 */
	void PrintTreeNode(Node* root);	/* eval 값 노드들을 출력 */
	void StartGame();			/* 게임 시작 */
	void EndGame();				/* 게임 종료 */
	void LoadGame();			/* 게임 불러오기 */

	/*	사용자 지정 변수 */

	GameBoard m_board;		/* 게임 보드 변수 */
	int m_levelA;			/* 컴퓨터 A의 LA-level */
	int m_levelB;			/* 컴퓨터 B의 LA-level */
	int m_startCom;			/* 시작하는 COM 종류 */
	int m_isLoad;			/* 게임이 로드 된 건지 구분 */
	int m_checkUndo;		/* 무르기를 했는지 여부 구분 */
	afx_msg void OnBnClickedB1();
	afx_msg void OnBnClickedB2();
	afx_msg void OnBnClickedB3();
	afx_msg void OnBnClickedB4();
	afx_msg void OnBnClickedB5();
	afx_msg void OnBnClickedB6();
	afx_msg void OnBnClickedB7();
	afx_msg void OnBnClickedB8();
	afx_msg void OnBnClickedB9();
	afx_msg void OnBnClickedB10();
	afx_msg void OnBnClickedB11();
	afx_msg void OnBnClickedB12();
	afx_msg void OnBnClickedB13();
	afx_msg void OnBnClickedB14();
	afx_msg void OnBnClickedB15();
	afx_msg void OnBnClickedB16();
	void IsClickCheck();
	afx_msg void OnBnClickedButtonSave();
};
